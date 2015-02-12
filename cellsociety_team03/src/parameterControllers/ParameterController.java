package parameterControllers;

import javafx.scene.Node;

public abstract class ParameterController {

    protected double myValue;

    public double getValue () {
        return myValue;
    }
    
    public abstract Node getNode ();
}
