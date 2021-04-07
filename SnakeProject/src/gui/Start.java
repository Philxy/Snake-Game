package gui;

import gui.Screen;
import javafx.application.Application;
import javafx.scene.paint.Color;
import snake.*;

import java.util.LinkedList;
import java.util.List;

public class Start {

    public static List<Food> food = new LinkedList<Food>();
    public static List<Obj> objects = new LinkedList<Obj>();

    public static void main(String[] args) {
        Snake snake1 = new Snake(5, 5, Direction.SOUTH, Color.YELLOW);
        Snake snake2 = new Snake(15, 15, Direction.NORTH, Color.PURPLE);

        objects.addAll(food);
        objects.addAll(snake1.getBodyParts());
        objects.addAll(snake2.getBodyParts());

        Application.launch(Screen.class);
    }

}
