package snake;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Food extends Obj {

    public static List<Food> foodList = new LinkedList<>();
    public static int foodEaten = 0;

    public Food(int x, int y) {
        super(x, y, Color.RED, false);
        foodList.add(this);
    }

    @Override
    public void remove() {
        foodList.remove(this);
        super.remove();
    }


}
