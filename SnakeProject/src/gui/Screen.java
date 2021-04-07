package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Main;
import snake.BodyPart;
import snake.Head;
import snake.Obj;


public class Screen extends Application {

    private final static int nrSquares = 20;
    private final static int sqSize = 50;
    private static GridPane root = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, nrSquares*sqSize, nrSquares*sqSize);
        drawBoard();
        drawObjects();

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private void drawObjects() {
        for(Obj obj: Start.objects) {
            if(obj instanceof Head) {
                Circle circ = new Circle(sqSize*0.5);
                circ.setFill(obj.getColor());
                root.add(circ, obj.getY(), obj.getX());
            } else if( obj instanceof BodyPart) {
                Rectangle rect = new Rectangle(sqSize, sqSize);
                root.add(rect, obj.getY(), obj.getX());
            }
        }
    }



    private void drawBoard() {
        for(int r = 0; r < nrSquares; r++) {
            for(int c = 0; c < nrSquares; c++) {
                Rectangle square = new Rectangle(sqSize, sqSize);
                if( (r+c) % 2 == 0) {
                    square.setFill(Color.GREEN);
                }
                else {
                    square.setFill(Color.LIGHTGREEN);
                }
                root.add(square, c, r);
            }
        }
    }
}
