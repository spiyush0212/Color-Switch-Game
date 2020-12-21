import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class QuitGameController {

    @FXML
    public Group goToGameScreen;
    public Group exitGame;
    public AnchorPane rootPane;

    public void goHome() throws IOException {
        System.out.println("GOING HOME FROM QUIT CONFIRMATION");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        rootPane.getChildren().setAll(pane);
//        Scene newscene = new Scene(pane);
//        Stage current = (Stage) rootPane.getScene().getWindow();
//        current.setScene(newscene);
    }

    public void quitGame() {
        System.out.println("EXITING GAME");
        Stage current = (Stage) rootPane.getScene().getWindow();
        current.close();
    }

}
