package snake;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

/**
 * World keeps track of the state of a snake game
 */
public class World {

    public final static int DELAY = 200;

    private final int size;

    private final Snake snake;
    private final Food food;

    private final Random random = new Random();

    private final BooleanProperty running = new SimpleBooleanProperty(false);

    private final BooleanProperty started = new SimpleBooleanProperty(false);

    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);

    private final IntegerProperty score = new SimpleIntegerProperty(0);

    public World(int size) {
        this.size = size;

        snake = new Snake(size / 2, size / 2, this);
        food = new Food();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(DELAY), event -> snake.move()));
        timeline.setCycleCount(Animation.INDEFINITE);
        running.addListener((property, oldValue, newValue) -> {
            if (newValue) {
                timeline.play();
            } else {
                timeline.pause();
            }
        });

        moveFoodRandomly();
    }

    public void moveFoodRandomly() {
        do {
            food.moveTo(random.nextInt(size), random.nextInt(size));
        } while (snake.isAt(food.getX(), food.getY()));
    }

    public void endGame() {
        running.set(false);
        gameOver.set(true);
    }

    public void resetGame() {
        running.set(false);
        started.set(false);
        gameOver.set(false);
        score.set(0);
        snake.reset();
        moveFoodRandomly();
    }

    public void setRunning(boolean running) {
        if (running) {
            started.set(true);
        }
        this.running.set(running);
    }

    public void start() {
        started.set(true);
        setRunning(true);
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public boolean isRunning() {
        return running.get();
    }

    public boolean isStarted() {
        return started.get();
    }

    public int getSize() {
        return size;
    }

    public int getScore() {
        return score.get();
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public BooleanProperty getRunningProperty() {
        return running;
    }

    public BooleanProperty getStartedProperty() {
        return started;
    }

    public IntegerProperty getScoreProperty() {
        return score;
    }

    public BooleanProperty getGameOverProperty() {
        return gameOver;
    }

    public boolean isGameOver() {
        return gameOver.get();
    }
}
