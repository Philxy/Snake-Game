package snake;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.*;

public class Food extends Obj{

    static List<Food> foodList = new LinkedList<>();

    public Food(int x, int y) {
        super(x, y);
        foodList.add(this);
    }

    @Override
    public Paint getColor() {
        return Color.RED;
    }

    public void eaten(){
        //woanders hinplatzieren
    }
}
