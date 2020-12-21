import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application
{
    private static MediaPlayer mediaPlayer;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        music();
        Parent loadTitle = FXMLLoader.load(getClass().getResource("Title.fxml"));
        Scene scene = new Scene(loadTitle, 450, 800);
        primaryStage.setTitle("Color Switch Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void music()
    {
        String s = "assets\\colorSwitchTrack.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
