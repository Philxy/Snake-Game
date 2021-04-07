package gui;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import snake.Direction;

public class Interaction implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {

        switch (event.getCode()) {
            case W:
                Start.snake1.setDirection(Direction.NORTH);
                break;
            case D:
                Start.snake1.setDirection(Direction.EAST);
                break;
            case S:
                Start.snake1.setDirection(Direction.SOUTH);
                break;
            case A:
                Start.snake1.setDirection(Direction.WEST);
                break;

            case UP:
                Start.snake2.setDirection(Direction.NORTH);
                break;
            case RIGHT:
                Start.snake2.setDirection(Direction.EAST);
                break;
            case DOWN:
                Start.snake2.setDirection(Direction.SOUTH);
                break;
            case LEFT:
                Start.snake2.setDirection(Direction.WEST);
                break;
        }

        if (event.getCode().equals(KeyCode.SPACE)) {
            Start.snake1.move();
            Start.snake2.move();
            Screen.updateObjects();
        }
    }
}

