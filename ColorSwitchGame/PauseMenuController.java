import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PauseMenuController implements Initializable {

    @FXML
    public Group resumeButton;
    @FXML
    public AnchorPane rootPane;

    public void goToHome() throws IOException {
        System.out.println("HOME BUTTON IN PAUSE MENU CLICKED");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        rootPane.getChildren().setAll(pane);
        FileOutputStream out = new FileOutputStream("isNewGame.txt");
        out.write(49);
        out.close();
    }

    public void saveGame() throws Exception {
        System.out.println("SAVE GAME CLICKED");

    }

    public void restartGame() throws IOException {
        System.out.println("RESTART GAME CLICKED");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        rootPane.getChildren().setAll(pane);
        FileOutputStream out = new FileOutputStream("isNewGame.txt");
        out.write(49);
        out.close();
    }

    public void buttonClicked() throws IOException {
        System.out.println("GAME RESUMED FROM PAUSE");
        FileOutputStream out = new FileOutputStream("isNewGame.txt");
        out.write(48);
        out.close();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        rootPane.getChildren().setAll(pane);

//        Stage current = (Stage) rootPane.getScene().getWindow();
//        current.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), resumeButton);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Integer.MAX_VALUE);
        scaleTransition.play();
    }
}
