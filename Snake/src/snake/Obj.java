package snake;

import javafx.scene.paint.Paint;

import java.util.LinkedList;
import java.util.List;

public abstract class Obj {

    public static List<Obj> objects = new LinkedList<>();

    protected int x;
    protected int y;
    protected Paint color;

    public Obj(final int x, final int y, final Paint color) {
        this.x = x;
        this.y = y;
        this.color = color;
        objects.add(this);
    }

    public static boolean onCoords(int x, int y){
        for (Obj obj: objects){
            if(obj.getX() == x && obj.getY() == y){
                return true;
            }
        }
        return false;
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

    public Paint getColor(){
        return color;
    };
}
