package gamecollection.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class SnakeGame extends Application {

    

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake");

        GamePanel gamePanel = new GamePanel();

        Button startButton = new Button("Start");
        Button restartButton = new Button("Restart");
        startButton.setOnAction(event -> {
            gamePanel.startGame();
            startButton.setDisable(true);
            restartButton.setDisable(false);
            gamePanel.requestFocus();
        });
        
        restartButton.setDisable(true);
        restartButton.setOnAction(event -> {
            gamePanel.restartGame();
            restartButton.setDisable(true);
            startButton.setDisable(false);
            gamePanel.requestFocus();
        });
        

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(gamePanel, startButton,restartButton);
        Scene scene = new Scene(stackPane);
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case UP:
                    gamePanel.setDirection(GamePanel.Direction.UP);
                    break;
                case DOWN:
                    gamePanel.setDirection(GamePanel.Direction.DOWN);
                    break;
                case LEFT:
                    gamePanel.setDirection(GamePanel.Direction.LEFT);
                    break;
                case RIGHT:
                    gamePanel.setDirection(GamePanel.Direction.RIGHT);
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

