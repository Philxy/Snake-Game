package snake;

import gui.Settings;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private Direction direction;
    private final ArrayList<BodyPart> body;
    private final Paint color;
    private final Paint headColor;
    private boolean outOfBounds;

    public void move() {
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        final int lastX = body.get(body.size() - 1).getX();
        final int lastY = body.get(body.size() - 1).getY();

        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setPosition(body.get(i - 1).getX(), body.get(i - 1).getY());
        }

        switch (direction) {
            case NORTH:
                if (headY == 0 && Settings.openBorder) {
                    body.get(0).setPosition(headX, Settings.gridSize - 1);
                } else if (headY == 0) {
                    outOfBounds = true;
                } else {
                    body.get(0).setPosition(headX, headY - 1);
                }
                break;
            case EAST:
                if (headX == Settings.gridSize - 1 && Settings.openBorder) {
                    body.get(0).setPosition(0, headY);
                } else if (headX == Settings.gridSize - 1) {
                    outOfBounds = true;
                } else {
                    body.get(0).setPosition(headX + 1, headY);
                }
                break;
            case SOUTH:
                if (headY == Settings.gridSize - 1 && Settings.openBorder) {
                    body.get(0).setPosition(headX, 0);
                } else if (headY == Settings.gridSize - 1) {
                    outOfBounds = true;
                } else {
                    body.get(0).setPosition(headX, headY + 1);
                }
                break;
            case WEST:
                if (headX == 0 && Settings.openBorder) {
                    body.get(0).setPosition(Settings.gridSize - 1, headY);
                } else if (headX == 0) {
                    outOfBounds = true;
                } else {
                    body.get(0).setPosition(headX - 1, headY);
                }
                break;
        }

        if (headOnFood()) {
            body.add(new BodyPart(lastX, lastY, color));
        }

    }

    /**
     * Only sets new direction if possible
     */
    public void setDirection(final Direction dir) {
        if ((dir.equals(Direction.NORTH) && !direction.equals(Direction.SOUTH)
                || (dir.equals(Direction.EAST) && !direction.equals(Direction.WEST))
                || (dir.equals(Direction.SOUTH) && !direction.equals(Direction.NORTH))
                || (dir.equals(Direction.WEST) && !direction.equals(Direction.EAST)))) {
            this.direction = dir;
        }
    }

    /**
     * Checks collision with itself or the border
     */
    public boolean checkCollision() {
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        for (int i = 1; i < body.size(); i++) {
            if (headX == body.get(i).getX() && headY == body.get(i).getY()) {
                return true;
            }
        }
        if (outOfBounds) {
            return true;
        }
        return false;
    }

    /**
     * Checks collision with itself, the border or other snake
     */
    public boolean checkCollision(final Snake otherSnake) {
        checkCollision();
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        for (int i = 0; i < otherSnake.body.size(); i++) {
            if (headX == otherSnake.body.get(i).getX() && headY == otherSnake.body.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the snakes head is on Food and replaces the food on another Position
     */
    private boolean headOnFood() {
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        for (Food foo : Food.foodList) {
            if (foo.getX() == headX && foo.getY() == headY) {
                foo.placeRandom();
                return true;
            }
        }
        return false;
    }

    public Snake(final int x, final int y, final Direction dir, final int startLength, final Color color, final Color headColor) {
        this.body = new ArrayList<>();
        this.body.add(new BodyPart(x, y, headColor, true));
        for (int i = 1; i < startLength; i++) {
            this.body.add(new BodyPart(x, y, color));
        }
        this.direction = dir;
        this.color = color;
        this.headColor = headColor;
        this.outOfBounds = false;

    }

    public List<BodyPart> getBodyParts() {
        return body;
    }

}
