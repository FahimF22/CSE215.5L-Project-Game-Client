package gamecollection;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameCollection extends Application {
    Label openingText = new Label("FRAZ\nGAME COLLECTION");
    Menu menu;
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        
        openingText.setFont(Font.font("Cambria Math", 12));
        openingText.setTextFill(Color.CORNFLOWERBLUE);
        fadeIn();
        scaleAnimation(primaryStage);
        root.getChildren().add(openingText);
        
        primaryStage.setTitle("FRAZ GAME COLLECTION");
        primaryStage.show();
    }
    
    public void scaleAnimation(Stage primaryStage) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(2500));
        scale.setNode(openingText);
        scale.setByX(5);
        scale.setByY(5);
        scale.play();
        scale.setOnFinished(e -> fadeOut(primaryStage));
    }
    
    public void fadeIn() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2500));
        fadeIn.setNode(openingText);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }
    
    public void fadeOut(Stage primaryStage) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(2500));
        fadeOut.setNode(openingText);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.play();
        fadeOut.setOnFinished(e -> sceneChange(primaryStage));
    }
    
    public void sceneChange(Stage primaryStage) {
        StackPane root = new StackPane();
        
        createMenu(root);
        
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
    }
    
    public void createMenu(StackPane root) {
        menu = new Menu();
        root.getChildren().add(menu.getStackPane());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
