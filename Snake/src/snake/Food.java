package snake;

import gui.Settings;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Food extends Obj {

    public static List<Food> foodList = new LinkedList<>();

    public Food(int x, int y) {
        super(x, y, Color.RED);
        foodList.add(this);
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
}
