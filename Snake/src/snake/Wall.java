package snake;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Wall extends Obj {

    public static List<Wall> walls = new LinkedList<>();

    public static void createWalls(final int count) {
        for (int i = 0; i < count; i++) {
            Wall wall = new Wall(0, 0);
            wall.placeRandom();
        }
    }

    public Wall(final int x, final int y) {
        super(x, y, Color.BLACK, true);
        walls.add(this);
    }

    @Override
    public void remove() {
        walls.remove(this);
        super.remove();
    }

    public static void removeAll() {
        for (Wall wall : walls) {
            wall.remove();
        }
    }

}
