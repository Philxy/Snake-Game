package snake;

import gui.Settings;
import javafx.scene.paint.Paint;

import java.util.LinkedList;
import java.util.List;

public abstract class Obj {

    public static List<Obj> objects = new LinkedList<>();

    protected int x;
    protected int y;
    protected Paint color;
    protected boolean collidable;

    public Obj(final int x, final int y, final Paint color, final boolean collidable) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.collidable = collidable;
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

    public void placeRandom() {
        int randX = (int) (Math.random() * Settings.gridSize);
        int randY = (int) (Math.random() * Settings.gridSize);
        while (Obj.onCoords(randX, randY)) {
            randX = (int) (Math.random() * Settings.gridSize);
            randY = (int) (Math.random() * Settings.gridSize);
        }
        x = randX;
        y = randY;
    }

    public boolean isCollidable(){
        return collidable;
    }

    public void remove() {
        objects.remove(this);
    }

    public Paint getColor(){
        return color;
    };
}
