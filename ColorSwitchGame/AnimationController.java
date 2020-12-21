import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnimationController implements Initializable
{
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Circle ball;
    @FXML
    public Group circularObject;
    @FXML
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(ball);
        tt.setDuration(Duration.seconds(1.2));
        tt.setToY(-263);
        tt.play();

        RotateTransition rt = new RotateTransition(Duration.seconds(2), circularObject);
        rt.setByAngle(360);

        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.seconds(0.2));
        ft.setNode(rootPane);
        ft.setFromValue(1);
        ft.setToValue(0);

        PauseTransition p = new PauseTransition(Duration.seconds(2.4));

        p.setOnFinished(e -> {
            try {
                updatePane();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        p.play();
        SequentialTransition st = new SequentialTransition(rt, ft);
        st.play();
    }

    public void updatePane() throws IOException
    {
        System.out.println("OPENING GAME MENU AFTER ANIMATION");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene newscene = new Scene(pane);
        Stage current = (Stage) rootPane.getScene().getWindow();
        current.setScene(newscene);
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }
}
