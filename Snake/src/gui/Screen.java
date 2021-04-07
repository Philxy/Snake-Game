package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import snake.Food;
import snake.Obj;


public class Screen extends Application {

    private final static int gridSize = Settings.gridSize;
    private final static int squareSize = Settings.squareSize;
    private static final GridPane root = new GridPane();
    Scene scene = new Scene(root, gridSize * squareSize, gridSize * squareSize);

    @Override
    public void start(Stage primaryStage) throws Exception {

        scene.setOnKeyPressed(new Interaction());
        drawBoard();
        updateObjects();

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void updateObjects() {
        while (root.getChildren().size() > gridSize * gridSize) {
            root.getChildren().remove(root.getChildren().size() - 1);
        }
        for (Obj obj : Obj.objects) {
            if (obj.getClass().equals(Food.class)) {
                Circle circ = new Circle(Settings.squareSize / 2f);
                circ.setFill(obj.getColor());
                root.add(circ, obj.getX(), obj.getY());
            } else {
                Polygon rect = new Polygon
                        (Settings.squareSize / 3f, 0.0, 2 * Settings.squareSize / 3f, 0.0,
                                Settings.squareSize, Settings.squareSize / 3f, Settings.squareSize, 2 * Settings.squareSize / 3f,
                                2 * Settings.squareSize / 3f, Settings.squareSize, Settings.squareSize / 3f, Settings.squareSize,
                                0, 2 * Settings.squareSize / 3f, 0, Settings.squareSize / 3f);
                rect.setFill(obj.getColor());
                root.add(rect, obj.getX(), obj.getY());
            }
        }
    }

    public static void drawBoard() {
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                Rectangle square = new Rectangle(squareSize, squareSize);
                if ((r + c) % 2 == 0) {
                    square.setFill(Color.rgb(60, 60, 60));
                } else {
                    square.setFill(Color.rgb(50, 50, 50));
                }
                root.add(square, c, r);
            }
        }
    }
}
