package gamecollection.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pong extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(PongLayoutSize.GAME_WIDTH, PongLayoutSize.GAME_HEIGHT);
        GraphicsContext draw = canvas.getGraphicsContext2D();
        Game game = new Game();
        
        // Set Target Score
        game.scoreLimit.setTranslateY(-200);
        game.scoreLimit.setMaxWidth(200);
        game.scoreLimit.setPromptText("Enter Target Score");
        game.startBtn.setTranslateY(-150);
        game.startBtn.setOnAction(event -> {
            try {
                game.targetScore = Integer.parseInt(game.scoreLimit.getText());
                game.scoreLimit.setVisible(false);
                game.startBtn.setVisible(false);
                game.isEndOfGame = false;
                game.player1Score = 0;
                game.player2Score = 0;
                game.gameStarted = false;
                
                canvas.setOnMouseClicked(MouseEvent -> {
                    game.gameStarted = true;
                    game.randomNum = (int) (Math.random() * 4) + 1;
                    // Randomize Ball Direction
                    switch (game.randomNum) {
                        case 1:
                            game.ballXSpeed *= -1;
                            break;
                        case 2:
                            game.ballYSpeed *= -1;
                            break;
                        case 3:
                            game.ballXSpeed *= -1;
                            game.ballYSpeed *= -1;
                            break;
                        default:
                            break;
                    }
                });
            } catch (NumberFormatException ex) {
                System.out.println("Enter Integer Numbers Only");
            }
        });
        
        
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            
            switch(code) {
                case W: 
                    if(game.paddle1YPos > 0)
                        game.paddle1YPos -= 135;
                    break;
                case S:
                    if(game.paddle1YPos < (PongLayoutSize.GAME_HEIGHT - PongLayoutSize.PADDLE_HEIGHT))
                        game.paddle1YPos += 135;
                    break;
                case UP:
                    if(game.paddle2YPos > 0)
                        game.paddle2YPos -= 135;
                    break;
                case DOWN:
                    if(game.paddle2YPos < (PongLayoutSize.GAME_HEIGHT - PongLayoutSize.PADDLE_HEIGHT))
                        game.paddle2YPos += 135;
                    break;
            }
        });
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), event -> game.runGame(draw)));
        animation.setCycleCount(Timeline.INDEFINITE);
        
        root.getChildren().add(canvas);
        root.getChildren().add(game.scoreLimit);
        root.getChildren().add(game.startBtn);
        Scene scene = new Scene(root);
        primaryStage.setTitle("PONG");
        primaryStage.setScene(scene);
        primaryStage.show();
        animation.play();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

