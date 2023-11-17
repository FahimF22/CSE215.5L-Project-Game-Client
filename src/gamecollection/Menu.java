package gamecollection;

import gamecollection.pong.Pong;
import gamecollection.snake.SnakeGame;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu {
    private final StackPane pane;
    Button game1 = new Button("Tic Tac Toe");
    Button game2 = new Button("Snake Game");
    Button game3 = new Button("Pong Game");
    Button game4 = new Button("Breakout Game");
    Button exit = new Button("Exit");
    
    public Menu() {
        pane = new StackPane();
        Label message = new Label("Choose a Game To Play");
        message.setFont(Font.font("Cambria Math", 45));
        message.setTranslateY(-300);
        
        game1.setMinSize(200, 80);
        game2.setMinSize(200, 80);
        game3.setMinSize(200, 80);
        game4.setMinSize(200, 80);
        exit.setMinSize(200, 80);
        
        game1.setTranslateY(-200);
        game2.setTranslateY(-100);
        game4.setTranslateY(100);
        exit.setTranslateY(200);
        
        game1.setOnAction(e -> {
            openTicTacToe();
        });
        
        game2.setOnAction(e -> {
            openSnakeGame();
        });
        
        game3.setOnAction(e -> {
            openPongGame();
        });
        
        game4.setOnAction(e -> {
            openBreakoutGame();
        });
        
        exit.setOnAction(e -> {
            System.exit(0);
        });
        
        pane.getChildren().add(message);
        pane.getChildren().add(game1);
        pane.getChildren().add(game2);
        pane.getChildren().add(game3);
        pane.getChildren().add(game4);
        pane.getChildren().add(exit);
    }
    
    public void openTicTacToe() {
        try {
            FXMLLoader newFxml = new FXMLLoader(getClass().getResource("tictactoe/GameLayout.fxml"));
            Parent root1 = (Parent) newFxml.load();
            Stage tictactoeStage = new Stage();
            tictactoeStage.setScene(new Scene(root1));
            tictactoeStage.setTitle("Tic Tac Toe");
            tictactoeStage.show();
        } catch (IOException ex) {
            
        }
    }
    
    public void openSnakeGame() {
        try {
            SnakeGame snakeGame = new SnakeGame();
            Stage snakeStage = new Stage();
            snakeGame.start(snakeStage);
        }
        catch(Exception ex)  {
            
        }
    }
    
    public void openPongGame() {
        try {
            Pong pongGame = new Pong();
            Stage pongStage = new Stage();
            pongGame.start(pongStage);
        }
        catch(Exception ex)  {
            
        }
    }
    
    public void openBreakoutGame() {
        try {
            FXMLLoader gameLayout = new FXMLLoader(getClass().getResource("brickbreak/gameLayout.FXML"));
            URL url = getClass().getResource("brickbreak/gameLayout.fxml");
            System.out.println(url);
            Parent root1 = (Parent) gameLayout.load();
            Stage breakoutStage = new Stage();
            breakoutStage.setScene(new Scene(root1));
            breakoutStage.setTitle("Brick Break");
            breakoutStage.show();
        }
        catch(IOException ex)  {
            
        }
    }
    
    public StackPane getStackPane() {
        return this.pane;
    }
}
