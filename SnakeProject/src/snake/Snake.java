package snake;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.*;

public class Snake {

    private Direction direction;
    private final ArrayList<BodyPart> body;
    private final Paint color;

    public void move() {
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        final int lastX = body.get(body.size() - 1).getX();
        final int lastY = body.get(body.size() - 1).getY();

        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setPosition(body.get(i - 1).getX(), body.get(i - 1).getY());
        }

        if (direction.equals(Direction.NORTH)) {
            body.get(0).setPosition(headX, headY - 1);
        } else if (direction.equals(Direction.EAST)) {
            body.get(0).setPosition(headX + 1, headY);
        } else if (direction.equals(Direction.SOUTH)) {
            body.get(0).setPosition(headX, headY + 1);
        } else if (direction.equals(Direction.WEST)) {
            body.get(0).setPosition(headX - 1, headY);
        }

        if (headOnFood()){
            body.add(new BodyPart(lastX, lastY));
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
            direction = dir;
        }
    }

    /**
     * Checks collision with itself
     */
    private boolean checkCollision() {
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        for (int i = 1; i < body.size(); i++) {
            if (headX == body.get(i).getX() && headY == body.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks collision with itself or other snake
     */
    private boolean checkCollision(final Snake otherSnake) {
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        for (int i = 1; i < this.body.size(); i++) {
            if (headX == this.body.get(i).getX() && headY == this.body.get(i).getY()) {
                return true;
            }
        }
        for (int i = 0; i < otherSnake.body.size(); i++) {
            if (headX == otherSnake.body.get(i).getX() && headY == otherSnake.body.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    private boolean headOnFood(){
        final int headX = body.get(0).getX();
        final int headY = body.get(0).getY();
        //....
        return false;
    }

    public Snake(final int x, final int y, final Direction dir, Color color) {
        body = new ArrayList<>();
        body.add(new Head(x, y));
        this.direction = dir;
        this.color = color;
    }

    public Paint getColor() {
        return this.color;
    }


    public List<BodyPart> getBodyParts() {
        for(BodyPart bP: body) {
            bP.setColor(this.color);
        }
        return body;
    }


}
