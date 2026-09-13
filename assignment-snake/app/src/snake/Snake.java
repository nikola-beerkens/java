package snake;

import java.util.LinkedList;
import java.util.List;

/**
 * Snake consists of segments, where this head segment keeps track of the other body segments
 */
public class Snake extends Segment {

    public interface SnakeSegmentListener {
        public void onNewSegment(Segment segment);
    }

    private Direction direction = Direction.RIGHT;

    private final World world;

    private final List<Segment> body = new LinkedList<>();

    private final List<SnakeSegmentListener> listeners = new LinkedList<>();

    private final int startX;
    private final int startY;

    public Snake(int x, int y, World world) {
        super(x, y);
        this.world = world;
        this.startX = x;
        this.startY = y;
    }

    public void move() {
        int newX = getX() + direction.getDX();
        int newY = getY() + direction.getDY();

        if (newX < 0 || newX >= world.getSize() || newY < 0 || newY >= world.getSize()
                || isAt(newX, newY)) {
            world.endGame();
            return;
        }

        boolean eating = world.getFood().getX() == newX && world.getFood().getY() == newY;
        if (eating) {
            Segment segment = body.isEmpty()
                    ? new Segment(getX(), getY())
                    : new Segment(body.get(body.size() - 1).getX(), body.get(body.size() - 1).getY());
            body.add(segment);
            for (SnakeSegmentListener listener : listeners) {
                listener.onNewSegment(segment);
            }
            world.setScore(world.getScore() + 1);
            world.moveFoodRandomly();
        }

        for (int index = body.size() - 1; index > 0; index--) {
            body.get(index).setPosition(body.get(index - 1).getX(), body.get(index - 1).getY());
        }
        if (!body.isEmpty()) {
            body.get(0).setPosition(getX(), getY());
        }
        setPosition(newX, newY);
    }

    public void addListener(SnakeSegmentListener listener) {
        listeners.add(listener);
    }

    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public boolean isAt(int x, int y) {
        if (getX() == x && getY() == y) {
            return true;
        }

        for (Segment segment : body) {
            if (segment.getX() == x && segment.getY() == y) {
                return true;
            }
        }

        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void reset() {
        direction = Direction.RIGHT;
        setPosition(startX, startY);
        body.clear();
    }
}
