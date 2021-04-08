package snake;

import gui.Settings;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Snake {

    private Direction direction;
    private final ArrayList<BodyPart> body;
    private final Paint color;
    private final Paint headColor;
    private boolean outOfBounds;
    private final int startLength;
    private boolean firstStep;

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

        firstStep = false;
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
     * Checks collision with collidable objects
     */
    public boolean checkCollision() {
        if (outOfBounds) {
            return true;
        }
        if (firstStep) {
            return false;
        }
        final BodyPart head = body.get(0);
        final int headX = head.getX();
        final int headY = head.getY();
        for (Obj obj : Obj.objects) {
            if (headX == obj.getX() && headY == obj.getY() && obj.isCollidable() && obj != head) {
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
                Food.foodEaten++;
                Settings.delay = (long) (60 * Math.exp(-Food.foodEaten / 20f) + 40);
                return true;
            }
        }
        return false;
    }

    /**
     * Resets snake to a given position and direction
     */
    public void reset(final int x, final int y, final Direction dir) {
        for (BodyPart bp : body) {
            body.remove(bp);
            bp.remove();
        }
        this.body.add(new BodyPart(x, y, headColor, true));
        for (int i = 1; i < startLength; i++) {
            this.body.add(new BodyPart(body.get(0).getX(), body.get(0).getY(), color));
        }
        this.direction = dir;
        this.firstStep = true;
        this.outOfBounds = false;
    }

    /**
     * Resets snake to a random position with random direction
     */
    public void resetRandom() {
        for (BodyPart bp : body) {
            body.remove(bp);
            bp.remove();
        }
        this.body.add(new BodyPart(0, 0, headColor, true));
        body.get(0).placeRandom();
        for (int i = 1; i < startLength; i++) {
            this.body.add(new BodyPart(body.get(0).getX(), body.get(0).getY(), color));
        }
        if (body.get(0).getX() < 3) {
            this.direction = Direction.EAST;
        } else if (body.get(0).getY() < 3) {
            this.direction = Direction.SOUTH;
        } else if (body.get(0).getX() > Settings.gridSize - 4) {
            this.direction = Direction.WEST;
        } else if (body.get(0).getY() > Settings.gridSize - 4) {
            this.direction = Direction.NORTH;
        } else {
            int rand = (int) (Math.random() * 4);
            if (rand == 0) {
                this.direction = Direction.NORTH;
            } else if (rand == 1) {
                this.direction = Direction.EAST;
            } else if (rand == 2) {
                this.direction = Direction.SOUTH;
            } else if (rand == 3) {
                this.direction = Direction.WEST;
            }
        }
        this.firstStep = true;
        this.outOfBounds = false;
    }

    public Snake(final int x, final int y, final Direction dir, final int startLength, final Color color, final Color headColor) {
        this.body = new ArrayList<>();
        this.body.add(new BodyPart(x, y, headColor, true));
        for (int i = 1; i < startLength; i++) {
            this.body.add(new BodyPart(body.get(0).getX(), body.get(0).getY(), color));
        }
        this.direction = dir;
        this.startLength = startLength;
        this.color = color;
        this.headColor = headColor;
        this.firstStep = true;
        this.outOfBounds = false;
    }
}
