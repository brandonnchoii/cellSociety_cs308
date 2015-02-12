package occupant.state.fire;

import occupant.state.StateType;
import javafx.scene.paint.Color;


public class Burning extends StateType {
    private static final Color BURNING_COLOR = Color.RED;
    private static final String type = "BURNING";

    public Burning () {
        setColor(BURNING_COLOR);
        setType(type);
    }

    // the next state of any and all burning locations is always Empty
    @Override
    public StateType update () {
        return new Empty();
    }
}
