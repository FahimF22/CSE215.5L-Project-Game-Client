package gamecollection.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacToeController {
    
    private boolean isEndOfGame = false;
    private boolean isStartPressed = false;
    private char playerTurn = 'X';
    
    @FXML
    private Button startButton;
    @FXML
    private Label message;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    
    
    public void setStartButtonEvent(ActionEvent event) {
        hideStartButton();
        updateMessage("Player X's Turn");
        isStartPressed = true;
        startNewGame();
    }
    
    public void startNewGame() {
        isEndOfGame = false;
        playerTurn = 'X';
        
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        
        btn1.setStyle("none");
        btn2.setStyle("none");
        btn3.setStyle("none");
        btn4.setStyle("none");
        btn5.setStyle("none");
        btn6.setStyle("none");
        btn7.setStyle("none");
        btn8.setStyle("none");
        btn9.setStyle("none");
    }
    
    public void hideStartButton() {
        startButton.setVisible(false);
    }
    
    public void showStartButton() {
        startButton.setVisible(true);
    }
    
    public void btn1Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn1.getText().isEmpty()) {
            btn1.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn2Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn2.getText().isEmpty()) {
            btn2.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn3Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn3.getText().isEmpty()) {
            btn3.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn4Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn4.getText().isEmpty()) {
            btn4.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn5Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn5.getText().isEmpty()) {
            btn5.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn6Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn6.getText().isEmpty()) {
            btn6.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn7Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn7.getText().isEmpty()) {
            btn7.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn8Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn8.getText().isEmpty()) {
            btn8.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public void btn9Action(ActionEvent event) {
        if(isStartPressed && !isEndOfGame && btn9.getText().isEmpty()) {
            btn9.setText(getPlayerTurn());
            changePlayerTurn();
            checkForWinner();
        }
    }
    
    public String getPlayerTurn() {
        return String.valueOf(this.playerTurn);
    }
    
    public void updateMessage(String message) {
        this.message.setText(message);
    }
    
    public void changePlayerTurn() {
        if(this.playerTurn == 'X') {
            this.playerTurn = 'O';
        }
        else {
            this.playerTurn = 'X';
        }
        
        updateMessage("Player " + this.playerTurn + "'s Turn");
    }
    
    public void checkForWinner() {
        checkRows();
        checkColumns();
        checkFirstDiagonal();
        checkSecondDiagonal();
        checkForDraw();
    }
    
    public void checkRows() {
        String winner;
        
        if(!isEndOfGame) {
            if (btn1.getText().equals(btn2.getText()) && btn1.getText().equals(btn3.getText()) && !btn1.getText().isEmpty()) {
                winner = btn1.getText();
                btn1.setStyle("-fx-background-color: #00A36C;");
                btn2.setStyle("-fx-background-color: #00A36C;");
                btn3.setStyle("-fx-background-color: #00A36C;");
            } else if (btn4.getText().equals(btn5.getText()) && btn4.getText().equals(btn6.getText()) && !btn4.getText().isEmpty()) {
                winner = btn4.getText();
                btn4.setStyle("-fx-background-color: #00A36C;");
                btn5.setStyle("-fx-background-color: #00A36C;");
                btn6.setStyle("-fx-background-color: #00A36C;");
            } else if (btn7.getText().equals(btn8.getText()) && btn7.getText().equals(btn9.getText()) && !btn7.getText().isEmpty()) {
                winner = btn7.getText();
                btn7.setStyle("-fx-background-color: #00A36C;");
                btn8.setStyle("-fx-background-color: #00A36C;");
                btn9.setStyle("-fx-background-color: #00A36C;");
            } else {
                return;
            }
            endGame(winner);
        }
    }
    
    public void checkColumns() {
        String winner;
        
        if(!isEndOfGame) {
            if (btn1.getText().equals(btn4.getText()) && btn1.getText().equals(btn7.getText()) && !btn1.getText().isEmpty()) {
                winner = btn1.getText();
                btn1.setStyle("-fx-background-color: #00A36C;");
                btn4.setStyle("-fx-background-color: #00A36C;");
                btn7.setStyle("-fx-background-color: #00A36C;");
            } else if (btn2.getText().equals(btn5.getText()) && btn2.getText().equals(btn8.getText()) && !btn2.getText().isEmpty()) {
                winner = btn2.getText();
                btn2.setStyle("-fx-background-color: #00A36C;");
                btn5.setStyle("-fx-background-color: #00A36C;");
                btn8.setStyle("-fx-background-color: #00A36C;");
            } else if (btn3.getText().equals(btn6.getText()) && btn3.getText().equals(btn9.getText()) && !btn3.getText().isEmpty()) {
                winner = btn3.getText();
                btn3.setStyle("-fx-background-color: #00A36C;");
                btn6.setStyle("-fx-background-color: #00A36C;");
                btn9.setStyle("-fx-background-color: #00A36C;");
            } else {
                return;
            }
            endGame(winner);
        }
    }
    
    public void checkFirstDiagonal() {
        if(!isEndOfGame) {
            if (btn1.getText().equals(btn5.getText()) && btn1.getText().equals(btn9.getText()) && !btn1.getText().isEmpty()) {
                String winner = btn1.getText();
                btn1.setStyle("-fx-background-color: #00A36C;");
                btn5.setStyle("-fx-background-color: #00A36C;");
                btn9.setStyle("-fx-background-color: #00A36C;");
                endGame(winner);
            }
        }
    }
    
    public void checkSecondDiagonal() {
        if(!isEndOfGame) {
            if (btn3.getText().equals(btn5.getText()) && btn3.getText().equals(btn7.getText()) && !btn3.getText().isEmpty()) {
                String winner = btn3.getText();
                btn3.setStyle("-fx-background-color: #00A36C;");
                btn5.setStyle("-fx-background-color: #00A36C;");
                btn7.setStyle("-fx-background-color: #00A36C;");
                endGame(winner);
            }
        }
    }
    
    public void checkForDraw() {
        if(!isEndOfGame) {
            if (btn1.getText().isEmpty() || btn2.getText().isEmpty() || btn3.getText().isEmpty()
                    || btn4.getText().isEmpty() || btn5.getText().isEmpty() || btn6.getText().isEmpty()
                    || btn7.getText().isEmpty() || btn8.getText().isEmpty() || btn9.getText().isEmpty()) {
                return;
            }

            isEndOfGame = true;
            updateMessage("-------DRAW-------");
            showStartButton();
        }
    }
    
    public void endGame(String winner) {
        isEndOfGame = true;
        updateMessage("Player " + winner + " Wins!!");
        showStartButton();
    }
}

