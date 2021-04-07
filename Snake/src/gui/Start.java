package gui;

import javafx.application.Application;
import javafx.scene.paint.Color;
import snake.*;

import java.util.LinkedList;
import java.util.List;

public class Start {

    static Snake snake1 = new Snake(2, 8, Direction.EAST,3, Color.YELLOWGREEN, Color.YELLOW);
    static Snake snake2 = new Snake(14, 8, Direction.WEST,3, Color.BLUE, Color.AQUA);

    static Food food = new Food(8, 8);


    public static void main(String[] args)  {


        Application.launch(Screen.class);

    }



}
