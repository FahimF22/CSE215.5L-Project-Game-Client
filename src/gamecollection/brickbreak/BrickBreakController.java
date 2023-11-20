package gamecollection.brickbreak;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Md. Fahim Fardin
 */
public class BrickBreakerController implements Initializable {
    
    @FXML
    private AnchorPane screen;
    @FXML
    private Circle ball;
    @FXML
    private Rectangle player;
    @FXML
    private Button startButton;
    @FXML
    private Rectangle bottomLine;
    @FXML
    private Text playerScore;
    
    
    Robot robot = new Robot();
    private ArrayList<Rectangle> bricks = new ArrayList<>();
    private double DirX = 1;
    private double DirY = 3;
    private double ballPosX = 312;
    private double ballPosY = 234;
    private int score=0;
    
    
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent t) {
            movePlayer();
            checkCollisionPlayer(player);
            ball.setLayoutX(ball.getLayoutX()+DirX);
            ball.setLayoutY(ball.getLayoutY()+DirY);
            
            if(bricks.isEmpty()){
                timeline.stop();
                startButton.setVisible(true);
            }
            else{
                bricks.removeIf(brick -> checkCollisionBrick(brick));
            }
            
            checkCollisionScreen(screen);
            checkCollisionBottomLine();
        }
    
    }));
    
    @FXML
    public void startButtonAction(ActionEvent event){
        startButton.setVisible(false);
        startGame();
    }
    
    public void startGame(){
        screen.getChildren().remove(playerScore);
        score=0;
        playerScore.setText(Integer.toString(0));
        ball.setLayoutX(ballPosX);
        ball.setLayoutY(ballPosY);
        createBricks();
        timeline.play();
    }

    public void createBricks(){
        double width = 560;
        double height = 150;

        int space = 1;

        for (double i = height; i > 0 ; i = i - 50) {
            for (double j = width; j > 0 ; j = j - 40) {
                if(space % 2 == 0){
                    Rectangle rectangle = new Rectangle(j,i,55,35);
                    rectangle.setFill(Color.YELLOW);
                    screen.getChildren().add(rectangle);
                    bricks.add(rectangle);
                }
                space++;
            }
        }
    }
    
    public void movePlayer(){
        Bounds bounds = screen.localToScreen(screen.getBoundsInLocal());
        double sceneXPos = bounds.getMinX();

        double mouseX = robot.getMouseX();
        double playerWidth = player.getWidth();

        if(mouseX >= sceneXPos + (playerWidth/2) && mouseX <= (sceneXPos + screen.getWidth()) - (playerWidth/2)){
            player.setLayoutX(mouseX - sceneXPos - (playerWidth/2));
        }
        else if (mouseX < sceneXPos + (playerWidth/2)){
            player.setLayoutX(0);
        }
        else if (mouseX > (sceneXPos + screen.getWidth()) - (playerWidth/2)){
            player.setLayoutX(screen.getWidth() - playerWidth);
        }
    }
    
    public void checkCollisionPlayer(Rectangle player){

        if(ball.getBoundsInParent().intersects(player.getBoundsInParent())){

            boolean rightBorder = ball.getLayoutX() >= ((player.getLayoutX() + player.getWidth()) - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= (player.getLayoutX() + ball.getRadius());
            boolean bottomBorder = ball.getLayoutY() >= ((player.getLayoutY() + player.getHeight()) - ball.getRadius());
            boolean topBorder = ball.getLayoutY() <= (player.getLayoutY() + ball.getRadius());

            if (rightBorder || leftBorder) {
                DirX *= -1;
            }
            if (bottomBorder || topBorder) {
                DirY *= -1;
            }
        }
    }
    
    public boolean checkCollisionBrick(Rectangle brick){

        if(ball.getBoundsInParent().intersects(brick.getBoundsInParent())){
            boolean rightBorder = ball.getLayoutX() >= ((brick.getX() + brick.getWidth()) - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= (brick.getX() + ball.getRadius());
            boolean bottomBorder = ball.getLayoutY() >= ((brick.getY() + brick.getHeight()) - ball.getRadius());
            boolean topBorder = ball.getLayoutY() <= (brick.getY() + ball.getRadius());

            if (rightBorder || leftBorder) {
                DirX *= -1;
                
                
            }
            if (bottomBorder || topBorder) {
                DirY *= -1;
                
            }
            screen.getChildren().remove(brick);
            screen.getChildren().remove(playerScore);
            score++;
            playerScore.setText(Integer.toString(score));
                
            screen.getChildren().add(playerScore);
            return true;
        }
        return false;
    }
    
    public void checkCollisionScreen(Node node){
        Bounds bounds = node.getBoundsInLocal();
        boolean rightBorder = ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius());
        boolean leftBorder = ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius());
        boolean bottomBorder = ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius());
        boolean topBorder = ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius());

        if (rightBorder || leftBorder) {
            DirX *= -1;
        }
        if (bottomBorder || topBorder) {
            DirY *= -1;
        }
    }


    

    public void checkCollisionBottomLine(){
        if(ball.getBoundsInParent().intersects(bottomLine.getBoundsInParent())){
            timeline.stop();
            bricks.forEach(brick -> screen.getChildren().remove(brick));
            bricks.clear();
            startButton.setVisible(true);
            

            System.out.println("Game over!");
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeline.setCycleCount(Animation.INDEFINITE);
    }
}

