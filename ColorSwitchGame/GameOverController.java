
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
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.net.URL;
        import java.util.ResourceBundle;

public class GameOverController implements Initializable
{
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Group restartButton;
    @FXML
    public Text currentScore, bestScore, stars;

    private Player player;

    public void goToHome(MouseEvent mouseEvent) throws IOException {
        System.out.println("HOME BUTTON CLICKED FROM GAME OVER MENU");
        FileOutputStream out = new FileOutputStream("isNewGame.txt");
        out.write(49);
        out.close();
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
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), restartButton);

        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Integer.MAX_VALUE);

        scaleTransition.play();

        try
        {
            FileInputStream fis = new FileInputStream("player.ser");
            ObjectInputStream os = new ObjectInputStream(fis);
            player = (Player)os.readObject();
            System.out.println("STATS player with score " + player.getTotalScore());
            currentScore.setText(""+player.getScore());
            bestScore.setText("" + player.getHighscore());
            stars.setText("" + player.getTotalScore());
        }

        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("NO PLAYER FOUND/ERROR IN DESERIALIZING");
//            e.printStackTrace();
            currentScore.setText("0");
            bestScore.setText("0");
            stars.setText("0");
        }
    }
}