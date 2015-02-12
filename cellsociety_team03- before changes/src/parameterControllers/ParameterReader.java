package parameterControllers;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import javafx.scene.Node;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


/**
 * Demonstrates a TextField control that allows you to enter text.
 *
 * @see javafx.scene.control.TextField
 * @resource Validators.css
 */
public class ParameterReader extends ParameterController {

    private StackPane rootSP;

    public ParameterReader (int minValue, int maxValue, int defaultValue) {
        String validatorCss = ParameterReader.class.getResource("Validators.css").toExternalForm();

        TextField dateField = new TextField();
        dateField.setMaxHeight(TextField.USE_PREF_SIZE);
        dateField.setText(String.valueOf(defaultValue));
        myValue=defaultValue;
        TextInputValidatorPane<TextField> pane = new TextInputValidatorPane<TextField>();
        pane.setContent(dateField);
        pane.setValidator(new Validator<TextField>() {
            public ValidationResult validate (TextField control) {
                try {
                    String text = control.getText();
                    if (text == null || text.trim().equals(""))
                        return null;
                    myValue = Integer.parseInt(text);
                    if (myValue < minValue || myValue > maxValue)
                        return new ValidationResult("Outside of Bounds",
                                                    ValidationResult.Type.ERROR);

                    return null; // succeeded
                }
                catch (Exception e) {
                    // failed
                    return new ValidationResult("Not an integer", ValidationResult.Type.ERROR);
                }
            }
        });

        rootSP = new StackPane();
        rootSP.setPadding(new Insets(12));
        rootSP.getChildren().add(pane);
        pane.getStylesheets().add(validatorCss);
    }

    public Node getNode () {
        return rootSP;
    }

    public static class ValidationResult {
        public enum Type {
            ERROR, WARNING, SUCCESS
        }

        private final String message;
        private final Type type;

        public ValidationResult (String message, Type type) {
            this.message = message;
            this.type = type;
        }

        public final String getMessage () {
            return message;
        }

        public final Type getType () {
            return type;
        }
    }

    private static interface Validator<C extends Control> {
        public ValidationResult validate (C control);
    }

    public static class ValidationEvent extends Event {
        public static final EventType<ValidationEvent> ANY =
                new EventType<ValidationEvent>(Event.ANY, "VALIDATION");

        private final ValidationResult result;

        public ValidationEvent (ValidationResult result) {
            super(ANY);
            this.result = result;
        }

        public final ValidationResult getResult () {
            return result;
        }
    }

    private abstract class ValidatorPane<C extends Control> extends Region {
        /**
         * The content for the validator pane is the control it should work with.
         */
        private ObjectProperty<C> content = new SimpleObjectProperty<C>(this, "content", null);

        public final C getContent () {
            return content.get();
        }

        public final void setContent (C value) {
            content.set(value);
        }

        public final ObjectProperty<C> contentProperty () {
            return content;
        }

        /**
         * The validator
         */
        private ObjectProperty<Validator<C>> validator =
                new SimpleObjectProperty<Validator<C>>(this, "validator");

        public final Validator<C> getValidator () {
            return validator.get();
        }

        public final void setValidator (Validator<C> value) {
            validator.set(value);
        }

        public final ObjectProperty<Validator<C>> validatorProperty () {
            return validator;
        }

        /**
         * The validation result
         */
        private ReadOnlyObjectWrapper<ValidationResult> validationResult =
                new ReadOnlyObjectWrapper<ValidationResult>(this, "validationResult");

        public final ValidationResult getValidationResult () {
            return validationResult.get();
        }

        public final ReadOnlyObjectProperty<ValidationResult> validationResultProperty () {
            return validationResult.getReadOnlyProperty();
        }

        /**
         * The event handler
         */
        private ObjectProperty<EventHandler<ValidationEvent>> onValidation =
                new SimpleObjectProperty<EventHandler<ValidationEvent>>(this, "onValidation");

        public final EventHandler<ValidationEvent> getOnValidation () {
            return onValidation.get();
        }

        public final void setOnValidation (EventHandler<ValidationEvent> value) {
            onValidation.set(value);
        }

        public final ObjectProperty<EventHandler<ValidationEvent>> onValidationProperty () {
            return onValidation;
        }

        public ValidatorPane () {
            content.addListener(new ChangeListener<Control>() {
                public void changed (ObservableValue<? extends Control> ov,
                                     Control oldValue,
                                     Control newValue) {
                    if (oldValue != null) getChildren().remove(oldValue);
                    if (newValue != null) getChildren().add(0, newValue);
                }
            });
        }

        protected void handleValidationResult (ValidationResult result) {
            getStyleClass().removeAll("validation-error", "validation-warning");
            if (result != null) {
                if (result.getType() == ValidationResult.Type.ERROR) {
                    getStyleClass().add("validation-error");
                }
                else if (result.getType() == ValidationResult.Type.WARNING) {
                    getStyleClass().add("validation-warning");
                }
            }
            validationResult.set(result);
            fireEvent(new ValidationEvent(result));
        }

        @Override
        protected void layoutChildren () {
            Control c = content.get();
            if (c != null) {
                c.resizeRelocate(0, 0, getWidth(), getHeight());
            }
        }

        @Override
        protected double computeMaxHeight (double d) {
            Control c = content.get();
            return c == null ? super.computeMaxHeight(d) : c.maxHeight(d);
        }

        @Override
        protected double computeMinHeight (double d) {
            Control c = content.get();
            return c == null ? super.computeMinHeight(d) : c.minHeight(d);
        }

        @Override
        protected double computePrefHeight (double d) {
            Control c = content.get();
            return c == null ? super.computePrefHeight(d) : c.prefHeight(d);
        }

        @Override
        protected double computePrefWidth (double d) {
            Control c = content.get();
            return c == null ? super.computePrefWidth(d) : c.prefWidth(d);
        }

        @Override
        protected double computeMaxWidth (double d) {
            Control c = content.get();
            return c == null ? super.computeMaxWidth(d) : c.maxWidth(d);
        }

        @Override
        protected double computeMinWidth (double d) {
            Control c = content.get();
            return c == null ? super.computeMinWidth(d) : c.minWidth(d);
        }
    }

    private class TextInputValidatorPane<C extends TextInputControl> extends ValidatorPane<C> {

        private InvalidationListener textListener = new InvalidationListener() {
            @Override
            public void invalidated (Observable o) {
                final Validator v = getValidator();
                final ValidationResult result =
                        v != null ?
                                 v.validate(getContent()) :
                                 new ValidationResult("", ValidationResult.Type.SUCCESS);

                handleValidationResult(result);
            }
        };

        public TextInputValidatorPane () {
            contentProperty().addListener(new ChangeListener<C>() {
                @Override
                public void changed (ObservableValue<? extends C> ov, C oldValue, C newValue) {
                    if (oldValue != null) oldValue.textProperty().removeListener(textListener);
                    if (newValue != null) newValue.textProperty().addListener(textListener);
                }
            });
        }

        public TextInputValidatorPane (C field) {
            this();
            setContent(field);
        }
    }
}
