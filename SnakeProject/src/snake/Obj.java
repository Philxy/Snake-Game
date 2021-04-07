package snake;

import javafx.scene.paint.Paint;

public abstract class Obj {

    private int x;
    private int y;

    public Obj(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }


    public abstract Paint getColor();
}
