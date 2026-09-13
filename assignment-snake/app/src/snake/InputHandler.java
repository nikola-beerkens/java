package snake;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Handles controls of a snake game, where WASD keys move the snake, Space starts the game, and R restarts
 */
public class InputHandler {

    private final EventHandler<KeyEvent> keyHandler;
    private final EventHandler<MouseEvent> mouseHandler;

    public InputHandler(World world) {
        Snake snake = world.getSnake();

        keyHandler = keyEvent -> {
            switch (keyEvent.getText().toLowerCase()) {
                case "w":
                    snake.setDirection(Direction.UP);
                    break;
                case "s":
                    snake.setDirection(Direction.DOWN);
                    break;
                case "a":
                    snake.setDirection(Direction.LEFT);
                    break;
                case "d":
                    snake.setDirection(Direction.RIGHT);
                    break;
                case " ":
                    // Space bar to start (no pause)
                    if (!world.isStarted()) {
                        world.start();
                    }
                    break;
                case "r":
                    // R key to restart after game over
                    if (world.isGameOver()) {
                        world.resetGame();
                    }
                    break;
                default:
                    break;
            }
            keyEvent.consume();
        };

        mouseHandler = mouseEvent -> {
            int x = (int) mouseEvent.getX() / SnakeGame.SCALE;
            int y = (int) mouseEvent.getY() / SnakeGame.SCALE;
            if (x >= 0 && x < world.getSize() && y >= 0 && y < world.getSize()) {
                world.getFood().moveTo(x, y);
            }
            mouseEvent.consume();
        };
    }

    public EventHandler<KeyEvent> getKeyHandler() {
        return keyHandler;
    }

    public EventHandler<MouseEvent> getMouseHandler() {
        return mouseHandler;
    }
}
