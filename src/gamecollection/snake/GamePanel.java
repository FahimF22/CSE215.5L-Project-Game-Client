package gamecollection.snake;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class GamePanel extends Pane {

    private static final int SCREEN_WIDTH = 750;
    private static final int SCREEN_HEIGHT = 750;
    private static final int UNIT_SIZE = 15;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];
    private int bodyParts = 3;
    private int highScore = 0;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private Direction direction = Direction.RIGHT;
    private boolean running = false;
    private final Random random;

    public GamePanel() {
        random = new Random();
        setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setStyle("-fx-background-color: black;");
        setFocusTraversable(true);
    }

    public void startGame() {
        bodyParts = 3;
        applesEaten = 0;
        direction = Direction.RIGHT;
        running = true;
        newApple();

        new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 2100000000 / 25) {
                    lastUpdate = now;
                    if (running) {
                        move();
                        checkApple();
                        checkCollisions();
                        draw();
                    } else {
                        stop();
                        gameOver();
                    }
                }
            }
        }.start();
    }
    public void restartGame() {
    // Reset variables
    bodyParts = 3;
    applesEaten = 0;
    direction = Direction.RIGHT;
    running = true;
    newApple();

    // Enable start button and disable restart button
    StackPane stackPane = (StackPane) getParent();
    stackPane.getChildren().stream()
            .filter(node -> node instanceof Button)
            .map(node -> (Button) node)
            .forEach(button -> {
                if ("Start".equals(button.getText())) {
                    button.setDisable(false);
                } else if ("Restart".equals(button.getText())) {
                    button.setDisable(true);
                }
            });

    // Clear previous game over text
    getChildren().removeIf(node -> node instanceof javafx.scene.text.Text || node instanceof javafx.scene.text.TextFlow);
}

    private void draw() {
        getChildren().clear();

        // Draw snake
        for (int i = 0; i < bodyParts; i++) {
            Rectangle snakePart = new Rectangle(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            snakePart.setFill(i == 0 ? Color.GREEN : new Color(45.0 / 255.0, 180.0 / 255.0, 0, 1));
            getChildren().add(snakePart);
        }

        // Draw apple
        Rectangle apple = new Rectangle(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
        apple.setFill(Color.RED);
        getChildren().add(apple);

        // Draw score
        javafx.scene.text.Text scoreText = new javafx.scene.text.Text("Score: " + applesEaten);
        scoreText.setFont(new javafx.scene.text.Font("Ink Free", 10));
        scoreText.setFill(Color.RED);
        scoreText.setLayoutX((SCREEN_WIDTH - 300) / 2);
        scoreText.setLayoutY(50);
        getChildren().add(scoreText);
        javafx.scene.text.Text highScoreText = new javafx.scene.text.Text("High Score: " + highScore);
        highScoreText.setFont(new javafx.scene.text.Font("Ink Free", 10));
        highScoreText.setFill(Color.RED);
        highScoreText.setLayoutX((SCREEN_WIDTH - 200) / 2);
        highScoreText.setLayoutY(50);
        getChildren().add(highScoreText);
    }

    private void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    private void move() {
         for (int i = bodyParts - 1; i > 0; i--) {
        x[i] = x[i - 1];
        y[i] = y[i - 1];
    }

    // Move the head based on the current direction
    switch (direction) {
        case UP:
            y[0] = y[0] - UNIT_SIZE;
            break;
        case DOWN:
            y[0] = y[0] + UNIT_SIZE;
            break;
        case LEFT:
            x[0] = x[0] - UNIT_SIZE;
            break;
        case RIGHT:
            x[0] = x[0] + UNIT_SIZE;
            break;
    }
    }

    private void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    private void checkCollisions() {
        // checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // checking head touches left border
        if (x[0] < 0) {
            running = false;
        }

        // checking head touches right border
        if (x[0] >= SCREEN_WIDTH) {
            running = false;
        }

        // checking head touches top border
        if (y[0] < 0) {
            running = false;
        }

        // checking head touches bottom border
        if (y[0] >= SCREEN_HEIGHT) {
            running = false;
        }
        if (applesEaten > highScore) {
        highScore = applesEaten;
        }
    }

    private void gameOver() {
        // Score
        javafx.scene.text.Text scoreText = new javafx.scene.text.Text("Score: " + applesEaten);
        scoreText.setFont(new javafx.scene.text.Font("Ink Free", 25));
        scoreText.setFill(Color.RED);
        scoreText.setLayoutX((SCREEN_WIDTH - 400) / 2);
        scoreText.setLayoutY(200);
        getChildren().add(scoreText);

        // Game Over text
        javafx.scene.text.Text gameOverText = new javafx.scene.text.Text("Game Over");
        gameOverText.setFont(new javafx.scene.text.Font("Ink Free", 75));
        gameOverText.setFill(Color.RED);
        gameOverText.setLayoutX((SCREEN_WIDTH - 400) / 2);
        gameOverText.setLayoutY(SCREEN_HEIGHT / 2);
        getChildren().add(gameOverText);
        
        //high score text
        javafx.scene.text.Text highScoreText = new javafx.scene.text.Text("High Score: " + highScore);
        highScoreText.setFont(new javafx.scene.text.Font("Ink Free", 25));
        highScoreText.setFill(Color.RED);
        highScoreText.setLayoutX((SCREEN_WIDTH - 400) / 2);
        highScoreText.setLayoutY(300);
        getChildren().add(highScoreText);
    }

   public Direction getDirection() {
        return direction;
   }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}


