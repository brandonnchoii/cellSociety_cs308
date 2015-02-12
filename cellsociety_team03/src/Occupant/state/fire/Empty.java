package occupant.state.fire;

import occupant.state.StateType;
import javafx.scene.paint.Color;

public class Empty extends StateType {
    private static final Color EMPTY_COLOR = Color.YELLOW;
    private static final String type = "EMPTY";

    public Empty () {
        setColor(EMPTY_COLOR);
        setType(type);
    }

    // empty will always remain empty
    @Override
    public StateType update () {
        return new Empty();
    }
}
