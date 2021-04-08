package gui;

import javafx.application.Application;
import javafx.scene.paint.Color;
import snake.*;

import java.util.LinkedList;
import java.util.List;

public class Start {

    static Snake snake1;
    static Snake snake2;

    static Food food = new Food(8, 8);


    public static void main(String[] args)  {


        Application.launch(Screen.class);

    }



}
