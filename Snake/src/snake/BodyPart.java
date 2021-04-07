package snake;

import javafx.scene.paint.Paint;

public class BodyPart extends Obj {

    private boolean isHead;

    public BodyPart(int x, int y, Paint color) {
        super(x, y, color);
        isHead = false;
    }

    public BodyPart(int x, int y, Paint color, boolean isHead) {
        super(x, y, color);
        this.isHead = isHead;
    }

    public boolean isHead(){
        return isHead;
    }

}
