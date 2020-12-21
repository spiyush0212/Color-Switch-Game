import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleController implements Initializable
{
    @FXML
    AnchorPane rootPane;
    @FXML
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        PauseTransition p = new PauseTransition(Duration.seconds(2));

        p.setOnFinished(e -> {
            try {
                loadGameAnimation();
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        p.play();

    }

    @FXML
    void loadGameAnimation() throws IOException
    {
        System.out.println("STARTING LOADING ANIMATION");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Animation.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void setStage(Stage stage){
        this.stage=stage;
        System.out.println(stage);
    }



}
