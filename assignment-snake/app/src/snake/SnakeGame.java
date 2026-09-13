package snake;

import javafx.beans.binding.Bindings;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;

/**
 * A JavaFX Pane that displays the snake game represented by the given world
 */
public class SnakeGame extends Pane {

    public static final int SCALE = 16;
    private final Pane bodySegmentsPane = new Pane();

    public SnakeGame(World world) {
        setPrefSize(world.getSize() * SCALE, world.getSize() * SCALE);

        Circle food = new Circle(SCALE / 2.0, Color.RED);
        food.layoutXProperty().bind(world.getFood().getXProperty().multiply(SCALE).add(SCALE / 2.0));
        food.layoutYProperty().bind(world.getFood().getYProperty().multiply(SCALE).add(SCALE / 2.0));
        getChildren().add(food);
        getChildren().add(bodySegmentsPane);

        Snake snake = world.getSnake();
        getChildren().add(createSegment(snake, Color.DARKGREEN));
        snake.addListener(segment -> bodySegmentsPane.getChildren().add(createSegment(segment, Color.GREEN)));

        // Listen for game reset to clear body segments
        world.getGameOverProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal && !newVal) {
                // Game has been reset (gameOver went from true to false)
                bodySegmentsPane.getChildren().clear();
            }
        });

        // Add Game Over label
        Label gameOverLabel = new Label("GAME OVER\nPress R to Restart");
        gameOverLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-text-alignment: center;");
        gameOverLabel.setLayoutX(world.getSize() * SCALE / 2 - 80);
        gameOverLabel.setLayoutY(world.getSize() * SCALE / 2 - 40);
        gameOverLabel.visibleProperty().bind(world.getGameOverProperty());
        getChildren().add(gameOverLabel);
    }

    private Rectangle createSegment(Segment segment, Color color) {
        Rectangle rectangle = new Rectangle(SCALE, SCALE, color);
        rectangle.xProperty().bind(segment.getXProperty().multiply(SCALE));
        rectangle.yProperty().bind(segment.getYProperty().multiply(SCALE));
        return rectangle;
    }

    public static Pane createUserInterface(World world) {
        VBox ui = new VBox();

        Label scoreText = new Label();
        Label runningText = new Label("Press 'space' to start");

        scoreText.textProperty().bind(world.getScoreProperty().asString("%d points"));
        runningText.textProperty().bind(Bindings.createStringBinding(
            () -> {
                if (world.isGameOver()) {
                    return "GAME OVER!";
                } else if (!world.isStarted()) {
                    return "Press 'space' to start";
                } else {
                    return "Running";
                }
            },
            world.getStartedProperty(), world.getGameOverProperty()));

        ui.getChildren().addAll(scoreText, runningText);

        return ui;
    }
}
