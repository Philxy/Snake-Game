package gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import snake.*;
import javafx.scene.text.*;


public class Screen extends Application {

    private final static int gridSize = Settings.gridSize;
    private final static int squareSize = Settings.squareSize;
    private static final GridPane root = new GridPane();
    static Scene scene = new Scene(root, gridSize * squareSize, gridSize * squareSize);

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene.setOnKeyPressed(new Interaction());

        Button p1 = new Button("Singleplayer");
        Button p2 = new Button("Multiplayer");
        p1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Start.snake1 = new Snake(gridSize / 2, gridSize / 2, Direction.EAST, 3, Color.YELLOWGREEN, Color.YELLOW);
                scene.setRoot(root);
                Settings.openBorder = false;
                drawBoard();
                updateObjects();
                run1();

            }
        });
        p2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Start.snake1 = new Snake((int) (gridSize / 3f), (int) (gridSize / 3f), Direction.EAST, 3, Color.YELLOWGREEN, Color.YELLOW);
                Start.snake2 = new Snake((int) (gridSize * (2f / 3f)), (int) (gridSize * (2f / 3f)), Direction.WEST, 3, Color.BLUE, Color.AQUA);
                scene.setRoot(root);
                drawBoard();
                updateObjects();
                Settings.openBorder = true;
                run2();

            }
        });

        Pane pane = new Pane();

        Text s = new Text();
        s.setText("Singleplayer");
        s.setY(squareSize * gridSize / 2f);
        s.setX(squareSize * gridSize * (3f / 4f));
        s.setStyle("-fx-font: 26 arial;");
        s.setFill(Color.WHITE);


        Text m = new Text();
        m.setText("Multiplayer");
        m.setY(squareSize * gridSize / 2f);
        m.setX(squareSize * gridSize * (1f / 4f));
        m.setFill(Color.WHITE);
        m.setStyle("-fx-font: 26 arial;");

        pane.getChildren().addAll(m, s);

        //pane.getChildren().add(p1);
        p1.setLayoutY((squareSize * gridSize) / 2f);
        p1.setLayoutX((squareSize * gridSize) * (2f / 3f));
        //pane.getChildren().add(p2);
        p2.setLayoutX((squareSize * gridSize) / 3f);
        p2.setLayoutY((squareSize * gridSize) / 2f);
        pane.setStyle("-fx-background-color: darkgrey;");
        scene.setRoot(pane);
        pane.setOnMouseClicked(new Mouse());

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
            } else if (obj.getClass().equals(Wall.class)) {
                Polygon wall = new Polygon
                        (0, 0,
                                Settings.squareSize / 3f, 0,
                                Settings.squareSize / 3f, Settings.squareSize / 6f,
                                2 * Settings.squareSize / 3f, Settings.squareSize / 6f,
                                2 * Settings.squareSize / 3f, 0,
                                Settings.squareSize, 0,
                                Settings.squareSize, Settings.squareSize / 3f,
                                5 * Settings.squareSize / 6f, Settings.squareSize / 3f,
                                5 * Settings.squareSize / 6f, 2 * Settings.squareSize / 3f,
                                Settings.squareSize, 2 * Settings.squareSize / 3f,
                                Settings.squareSize, Settings.squareSize,
                                2 * Settings.squareSize / 3f, Settings.squareSize,
                                2 * Settings.squareSize / 3f, 5 * Settings.squareSize / 6f,
                                Settings.squareSize / 3f, 5 * Settings.squareSize / 6f,
                                Settings.squareSize / 3f, Settings.squareSize,
                                0, Settings.squareSize,
                                0, 2 * Settings.squareSize / 3f,
                                Settings.squareSize / 6f, 2 * Settings.squareSize / 3f,
                                Settings.squareSize / 6f, Settings.squareSize / 3f,
                                0, Settings.squareSize / 3f);
                wall.setFill(obj.getColor());
                root.add(wall, obj.getX(), obj.getY());
            } else {
                Polygon snakeBody = new Polygon
                        (Settings.squareSize / 3f, 0.0, 2 * Settings.squareSize / 3f, 0.0,
                                Settings.squareSize, Settings.squareSize / 3f, Settings.squareSize, 2 * Settings.squareSize / 3f,
                                2 * Settings.squareSize / 3f, Settings.squareSize, Settings.squareSize / 3f, Settings.squareSize,
                                0, 2 * Settings.squareSize / 3f, 0, Settings.squareSize / 3f);
                snakeBody.setFill(obj.getColor());
                root.add(snakeBody, obj.getX(), obj.getY());
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

    public static void run1() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(Settings.delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Start.snake1.checkCollision()) {
                    return;
                }
                Start.snake1.move();
                updateObjects();
            }
        };
        timer.start();
    }


    public static void run2() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(Settings.delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Start.snake1.checkCollision() || Start.snake2.checkCollision()) {
                    return;
                }
                Start.snake1.move();
                Start.snake2.move();
                updateObjects();
            }
        };
        timer.start();
    }

    public static void setRoot() {
        scene.setRoot(root);
    }
}

