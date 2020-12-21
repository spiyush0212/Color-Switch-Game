import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckGameController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    @FXML
    public Text currentScore, stars;

    private Player player;

    public void gamePlayAgain(MouseEvent mouseEvent) throws IOException {
        System.out.println("GAME RESUMED FROM GAME OVER MENU");
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
//        Parent gameMenu = fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(gameMenu));
//        stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        rootPane.getChildren().setAll(pane);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent gameMenu = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(gameMenu));
        stage.show();
    }

    public void goToHome(MouseEvent mouseEvent) throws IOException {
        System.out.println("HOME BUTTON CLICKED FROM GAME OVER MENU");
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        rootPane.getChildren().setAll(pane);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent gameMenu = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(gameMenu));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            FileInputStream fis = new FileInputStream("player.ser");
            ObjectInputStream os = new ObjectInputStream(fis);
            player = (Player)os.readObject();
            System.out.println("STATS player with score " + player.getTotalScore());
            currentScore.setText(""+player.getScore());
            stars.setText("" + player.getTotalScore());
        }

        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("NO PLAYER FOUND/ERROR IN DESERIALIZING");
//            e.printStackTrace();
            currentScore.setText("0");
            stars.setText("0");
        }
    }


}
