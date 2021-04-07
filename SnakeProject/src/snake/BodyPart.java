package snake;

import javafx.scene.paint.Paint;

public class BodyPart extends Obj {

    private Paint color;

    public BodyPart(int x, int y) {
        super(x, y);
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public Paint getColor() {
        return color;
    }


}
