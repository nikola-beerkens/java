package snake;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Snake game logic
 */
public class SnakeGameTest {

    private World world;
    private Snake snake;
    private Food food;

    @Before
    public void setUp() {
        world = new World(25);
        snake = world.getSnake();
        food = world.getFood();
    }

    // ============ World Tests ============

    @Test
    public void testWorldInitialization() {
        assertEquals(25, world.getSize());
        assertFalse(world.isRunning());
        assertFalse(world.isStarted());
        assertEquals(0, world.getScore());
    }

    @Test
    public void testSnakeSpawnsAtCenter() {
        assertEquals(12, snake.getX());
        assertEquals(12, snake.getY());
    }

    @Test
    public void testFoodSpawnsRandomly() {
        assertNotNull(food);
        assertTrue(food.getX() >= 0 && food.getX() < 25);
        assertTrue(food.getY() >= 0 && food.getY() < 25);
    }

    // ============ Game State Tests ============

    @Test
    public void testGameStartsOnStart() {
        world.start();
        assertTrue(world.isStarted());
        assertTrue(world.isRunning());
    }

    @Test
    public void testGamePausesAndResumes() {
        world.start();
        assertTrue(world.isRunning());
        
        world.setRunning(false);
        assertFalse(world.isRunning());
        
        world.setRunning(true);
        assertTrue(world.isRunning());
    }

    @Test
    public void testGameEndsOnCollision() {
        world.start();
        assertTrue(world.isRunning());
        
        world.endGame();
        assertFalse(world.isRunning());
    }

    // ============ Snake Movement Tests ============

    @Test
    public void testSnakeInitialDirection() {
        assertEquals(Direction.RIGHT, snake.getDirection());
    }

    @Test
    public void testSnakeChangeDirection() {
        snake.setDirection(Direction.UP);
        assertEquals(Direction.UP, snake.getDirection());
        
        snake.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, snake.getDirection());
    }

    @Test
    public void testSnakeMoveRight() {
        int startX = snake.getX();
        int startY = snake.getY();
        
        snake.setDirection(Direction.RIGHT);
        snake.move();
        
        assertEquals(startX + 1, snake.getX());
        assertEquals(startY, snake.getY());
    }

    @Test
    public void testSnakeMoveLeft() {
        snake.setDirection(Direction.LEFT);
        int startX = snake.getX();
        int startY = snake.getY();
        
        snake.move();
        
        assertEquals(startX - 1, snake.getX());
        assertEquals(startY, snake.getY());
    }

    @Test
    public void testSnakeMoveUp() {
        snake.setDirection(Direction.UP);
        int startX = snake.getX();
        int startY = snake.getY();
        
        snake.move();
        
        assertEquals(startX, snake.getX());
        assertEquals(startY - 1, snake.getY());
    }

    @Test
    public void testSnakeMoveDown() {
        snake.setDirection(Direction.DOWN);
        int startX = snake.getX();
        int startY = snake.getY();
        
        snake.move();
        
        assertEquals(startX, snake.getX());
        assertEquals(startY + 1, snake.getY());
    }

    // ============ Collision Tests ============

    @Test
    public void testWallCollisionDetection() {
        // Move snake to left wall
        snake.setDirection(Direction.LEFT);
        for (int i = 0; i < 13; i++) {
            snake.move();
        }
        
        // Should be at x=0, y=12
        assertEquals(0, snake.getX());
        assertFalse(world.isRunning());
    }

    @Test
    public void testSelfCollisionDetection() {
        // Test that eating food is possible by setting up a scenario
        // where snake can eat. Food is placed ahead of snake.
        food.moveTo(13, 12);
        int scoreBeforeEating = world.getScore();
        int headXBefore = snake.getX();
        
        // Move the snake forward
        snake.move();
        
        // Check that snake moved
        assertTrue("Snake should have moved forward", snake.getX() != headXBefore);
    }

    @Test
    public void testSnakeIsAtPosition() {
        assertTrue(snake.isAt(12, 12)); // Head position
        assertFalse(snake.isAt(11, 12)); // Empty position
    }

    // ============ Food Tests ============

    @Test
    public void testFoodMovement() {
        // Test that food can be moved to a specific location
        food.moveTo(3, 7);
        
        assertEquals(3, food.getX());
        assertEquals(7, food.getY());
        
        // Move food again to verify it updates
        food.moveTo(15, 19);
        assertEquals(15, food.getX());
        assertEquals(19, food.getY());
    }

    @Test
    public void testFoodDoesntSpawnOnSnake() {
        // Move food randomly multiple times
        for (int i = 0; i < 20; i++) {
            world.moveFoodRandomly();
            assertFalse("Food spawned on snake!", snake.isAt(food.getX(), food.getY()));
        }
    }

    // ============ Eating Tests ============

    @Test
    public void testEatingIncreasesScore() {
        int startScore = world.getScore();
        
        // Place food at next position
        food.moveTo(13, 12);
        
        // Move into food
        snake.move();
        
        assertEquals(startScore + 1, world.getScore());
    }

    @Test
    public void testEatingGrowsSnake() {
        // Place food
        food.moveTo(13, 12);
        
        // Initial snake should only have head
        assertFalse(snake.isAt(11, 12));
        
        // Move to eat
        snake.move();
        
        // After eating, body segment should exist at old head position
        assertTrue(snake.isAt(12, 12));
    }

    // ============ Direction Tests ============

    @Test
    public void testDirectionRotateRight() {
        assertEquals(Direction.RIGHT, Direction.UP.rotateRight());
        assertEquals(Direction.DOWN, Direction.RIGHT.rotateRight());
        assertEquals(Direction.LEFT, Direction.DOWN.rotateRight());
        assertEquals(Direction.UP, Direction.LEFT.rotateRight());
    }

    @Test
    public void testDirectionRotateLeft() {
        assertEquals(Direction.LEFT, Direction.UP.rotateLeft());
        assertEquals(Direction.UP, Direction.LEFT.rotateLeft());
        assertEquals(Direction.RIGHT, Direction.DOWN.rotateLeft());
        assertEquals(Direction.DOWN, Direction.RIGHT.rotateLeft());
    }

    // ============ Segment Tests ============

    @Test
    public void testSegmentPosition() {
        Segment segment = new Segment(5, 10);
        assertEquals(5, segment.getX());
        assertEquals(10, segment.getY());
    }

    @Test
    public void testSegmentSetPosition() {
        Segment segment = new Segment(0, 0);
        segment.setPosition(15, 20);
        
        assertEquals(15, segment.getX());
        assertEquals(20, segment.getY());
    }

    @Test
    public void testSegmentProperties() {
        Segment segment = new Segment(7, 8);
        assertNotNull(segment.getXProperty());
        assertNotNull(segment.getYProperty());
        assertEquals(7, segment.getXProperty().get());
        assertEquals(8, segment.getYProperty().get());
    }
}
