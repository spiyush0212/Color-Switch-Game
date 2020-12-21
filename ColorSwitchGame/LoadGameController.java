import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoadGameController
{
    @FXML
    private AnchorPane loadGameRoot;
    @FXML
    private TextField input;

    @FXML
    void handleMouseClick() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
        AnchorPane pane=fxmlLoader.load();
        loadGameRoot.getChildren().setAll(pane);
        FileOutputStream out = new FileOutputStream("saveGame.txt");
        out.write(48+Integer.parseInt(input.getText()));
        System.out.println(input.getText());
        out.close();
    }

    @FXML
    public void goToHome() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        loadGameRoot.getChildren().setAll(pane);
    }
}