package gamecollection.pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Game {
    public final int paddle1XPos = 0;
    public double paddle1YPos = PongLayoutSize.GAME_HEIGHT / 2;
    public final int paddle2XPos = PongLayoutSize.GAME_WIDTH - PongLayoutSize.PADDLE_WIDTH;
    public double paddle2YPos = PongLayoutSize.GAME_HEIGHT / 2;
    public double ballXPos = PongLayoutSize.GAME_WIDTH / 2;
    public double ballYPos = PongLayoutSize.GAME_HEIGHT / 2;
    public int ballXSpeed = 1;
    public int ballYSpeed = 1;
    public int player1Score = 0;
    public int player2Score = 0;
    public int targetScore = 1;
    public int count = 0;
    public int randomNum;
    public boolean gameStarted = false;
    public boolean isEndOfGame = false;
    TextField scoreLimit = new TextField();
    Button startBtn = new Button("Start Game");
    
    public void drawBackground(GraphicsContext draw) {
        // Background
        draw.setFill(Color.BLACK);
        draw.fillRect(0, 0, PongLayoutSize.GAME_WIDTH, PongLayoutSize.GAME_HEIGHT);
        
        // Text
        draw.setFill(Color.RED);
        draw.setFont(Font.font("Cambria Math", 35));
    }
    
    public void drawPaddle(GraphicsContext draw) {
        draw.setFill(Color.CORNFLOWERBLUE);
        draw.fillRect(paddle1XPos, paddle1YPos, PongLayoutSize.PADDLE_WIDTH, PongLayoutSize.PADDLE_HEIGHT);
        draw.fillRect(paddle2XPos, paddle2YPos, PongLayoutSize.PADDLE_WIDTH, PongLayoutSize.PADDLE_HEIGHT);
    }
    
    public void drawScore(GraphicsContext draw) {
        draw.setFill(Color.WHITE);
        draw.fillText("Score", PongLayoutSize.GAME_WIDTH / 2, 45);
        draw.fillText(String.valueOf(player1Score), (PongLayoutSize.GAME_WIDTH / 2 - 50), 95);
        draw.fillText(String.valueOf(player2Score), (PongLayoutSize.GAME_WIDTH / 2 + 50), 95);
        draw.setStroke(Color.WHITE);
        draw.strokeLine((PongLayoutSize.GAME_WIDTH / 2), 55, (PongLayoutSize.GAME_WIDTH / 2), 105);
    }
    
    public void runGame(GraphicsContext draw) {
        drawBackground(draw);
        startGame(draw);
        getWinner(draw);
        drawPaddle(draw);
        drawScore(draw);
    }
    
    public void startGame(GraphicsContext draw) {
        if(!isEndOfGame) {
            newRound(draw);
            
            // Ball Stays inside the window
            if (ballYPos >= (PongLayoutSize.GAME_HEIGHT - PongLayoutSize.BALL_RADIUS) || ballYPos <= 0) {
                ballYSpeed *= -1;
            }
            
            checkCollision();
            score();
        }
    }
    
    public void newRound(GraphicsContext draw) {
        if (gameStarted) {
            // Ball starts to move
            ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;

            // Draw Ball
            draw.fillOval(ballXPos, ballYPos, PongLayoutSize.BALL_RADIUS, PongLayoutSize.BALL_RADIUS);

        } else {
            // Start Game Message
            draw.setTextAlign(TextAlignment.CENTER);
            draw.fillText("Mouse Click To Start Game", PongLayoutSize.GAME_WIDTH / 2, PongLayoutSize.GAME_HEIGHT / 2);

            // Reset Ball Position
            ballXPos = PongLayoutSize.GAME_WIDTH / 2;
            ballYPos = PongLayoutSize.GAME_HEIGHT / 2;

            //Reset Ball Speed
            ballXSpeed = 1;
            ballYSpeed = 1;
        }
    }
    
    public void checkCollision() {
        if (((ballXPos) <= PongLayoutSize.PADDLE_WIDTH && ballYPos >= paddle1YPos && ballYPos <= paddle1YPos + (PongLayoutSize.PADDLE_HEIGHT))
                || ((ballXPos + PongLayoutSize.BALL_RADIUS) >= (PongLayoutSize.GAME_WIDTH - PongLayoutSize.PADDLE_WIDTH) && ballYPos >= paddle2YPos
                && ballYPos <= paddle2YPos + (PongLayoutSize.PADDLE_HEIGHT))) {
            ballXSpeed *= -1;

            count++;
            // Increase Speed
            if (count % 2 == 0) {
                ballYSpeed += 1 * Math.signum(ballYSpeed);
                ballXSpeed += 1 * Math.signum(ballXSpeed);
            }
        }
    }
    
    public void score() {
        if (ballXPos > PongLayoutSize.GAME_WIDTH - PongLayoutSize.PADDLE_WIDTH) {
            player1Score++;
            gameStarted = false;
        } else if (ballXPos < PongLayoutSize.PADDLE_WIDTH - PongLayoutSize.BALL_RADIUS) {
            player2Score++;
            gameStarted = false;
        }
    }
    
    public void getWinner(GraphicsContext draw) {
        if(player1Score == targetScore) {
            isEndOfGame = true;
            draw.setFill(Color.GREEN);
            draw.fillText("Player 1 Wins", PongLayoutSize.GAME_WIDTH / 2, PongLayoutSize.GAME_HEIGHT / 2);
            scoreLimit.setVisible(true);
            startBtn.setVisible(true);
        }
        else if(player2Score == targetScore) {
            isEndOfGame = true;
            draw.setFill(Color.GREEN);
            draw.fillText("Player 2 Wins", PongLayoutSize.GAME_WIDTH / 2, PongLayoutSize.GAME_HEIGHT / 2);
            scoreLimit.setVisible(true);
            startBtn.setVisible(true);
        }
    }
}

