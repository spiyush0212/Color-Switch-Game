import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    public AnchorPane pane;
    public Group circle2;
    public Group circle3;
    public Group button;
    public Group arrowleft;
    public Group arrowright;

    public void openGame() throws IOException {
        System.out.println("CLICKED PLAY GAME ON MAIN MENU");
        FileOutputStream out = new FileOutputStream("isNewGame.txt");
        out.write(49);
        out.close();
        AnchorPane npane = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        pane.getChildren().setAll(npane);

//        Scene newscene = new Scene(npane);
//        Stage current = (Stage) pane.getScene().getWindow();
//        current.setScene(newscene);
    }

    public void openExitMenu() throws IOException {
        System.out.println("CLICKED EXIT GAME ON MAIN MENU");
        AnchorPane npane = FXMLLoader.load(getClass().getResource("QuitGame.fxml"));
        pane.getChildren().setAll(npane);
//        Scene newscene = new Scene(npane);
//        Stage current = (Stage) pane.getScene().getWindow();
//        current.setScene(newscene);
    }

    public void openStatsPage() throws IOException {
        System.out.println("CLICKED STATS ON MAIN MENU");
        AnchorPane npane = FXMLLoader.load(getClass().getResource("StatsPage.fxml"));
        pane.getChildren().setAll(npane);
    }

    public void loadGame() throws IOException {
        System.out.println("LOAD GAME CLICKED");
        AnchorPane npane = FXMLLoader.load(getClass().getResource("LoadGame.fxml"));
        pane.getChildren().setAll(npane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //        pane.setOpacity(0);
        FadeTransition fo = new FadeTransition();
        fo.setDuration(Duration.seconds(0.2));
        fo.setNode(pane);
        fo.setFromValue(0);
        fo.setToValue(1);
        fo.play();

//        System.out.println("y");

        RotateTransition rt2 = new RotateTransition(Duration.seconds(3), circle2);
        RotateTransition rt3 = new RotateTransition(Duration.seconds(3), circle3);
        ScaleTransition st = new ScaleTransition(Duration.seconds(2), button);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1), arrowleft);
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1), arrowright);

        rt2.setInterpolator(Interpolator.LINEAR);
        rt3.setInterpolator(Interpolator.LINEAR);
        rt2.setCycleCount(-1);
        rt3.setCycleCount(-1);
        rt2.setByAngle(-360);
        rt3.setByAngle(360);

        st.setToX(1.2);
        st.setToY(1.2);
        st.setAutoReverse(true);
        st.setCycleCount(-1);

        tt.setToX(8);
        tt.setAutoReverse(true);
        tt.setCycleCount(-1);
        tt2.setToX(-8);
        tt2.setAutoReverse(true);
        tt2.setCycleCount(-1);

        ParallelTransition pt = new ParallelTransition(rt2, rt3, st, tt, tt2);
        pt.play();
    }
}