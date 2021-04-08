package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import snake.Direction;
import snake.Snake;
import snake.Wall;
import sun.awt.windows.WLightweightFramePeer;

public class Mouse implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        double x = event.getX();

        if(x > Settings.gridSize*Settings.squareSize /2f) {
            Start.snake1 = new Snake(Settings.gridSize/2, Settings.gridSize/2, Direction.EAST,3, Color.YELLOWGREEN, Color.YELLOW);
            Wall.createWalls(Settings.wallCount);
            Screen.setRoot();
            Settings.openBorder = false;
            Screen.drawBoard();
            Screen.updateObjects();
            Screen.run1();
        }
        if(x < Settings.gridSize*Settings.squareSize /2f) {
            Start.snake1 = new Snake((int)(Settings.gridSize/3f), (int)(Settings.gridSize/3f), Direction.EAST,3, Color.YELLOWGREEN, Color.YELLOW);
            Start.snake2 = new Snake((int) (Settings.gridSize*(2f/3f)), (int) (Settings.gridSize*(2f/3f)), Direction.WEST,3, Color.BLUE, Color.AQUA);
            Wall.createWalls(Settings.wallCount);
            Screen.setRoot();
            Screen.drawBoard();
            Screen.updateObjects();
            Settings.openBorder = true;
            Screen.run2();
        }

    }
}
