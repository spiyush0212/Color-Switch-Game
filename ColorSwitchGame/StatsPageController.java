import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StatsPageController
{
    @FXML
    public AnchorPane pane;
    @FXML
    public Text totalJumps, stars, highScore;
    private Player player;

    public void initialize() {
        try
        {
            FileInputStream fis = new FileInputStream("player.ser");
            ObjectInputStream os = new ObjectInputStream(fis);
            player = (Player)os.readObject();
            System.out.println("STATS player with score " + player.getTotalScore());
            totalJumps.setText("" + player.getTotalScreenTouches());
            stars.setText("" + player.getTotalScore());
            highScore.setText("" + player.getHighscore());
        }

        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("NO PLAYER FOUND/ERROR IN DESERIALIZING");
//            e.printStackTrace();
            totalJumps.setText("0");
            stars.setText("0");
            highScore.setText("0");
        }
    }

    public void goHome() throws IOException {
        System.out.println("HOME BUTTON PRESSED ON STATS SCREEN");
        AnchorPane npane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        pane.getChildren().setAll(npane);
//        Scene newscene = new Scene(npane);
//        Stage current = (Stage) pane.getScene().getWindow();
//        current.setScene(newscene);

    }

}
