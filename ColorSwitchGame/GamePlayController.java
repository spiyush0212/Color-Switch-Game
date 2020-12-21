import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class GamePlayController implements Initializable {
    @FXML
    public AnchorPane root;
    public Stage primaryStage;

    private boolean isPaused = true;
    private boolean godMode = false;
    private boolean isNewGame = false;
    private ArrayList<Touchable> obstacles;
    private Player player;
    private Cross cross;
    private Ring ring;
    private Star star1, star2, star3, star4, star5;
    private Box box;
    private SwitchColour switchColour1, switchColour2, switchColour3;
    private Ball ball;
    private Horizontal horizontal;
    int difficulty = -4;

    public void loadgameOver() throws IOException
    {
        FileOutputStream fos = new FileOutputStream("player.ser");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(player);
        System.out.println("GAME OVER - OBSTACLE HIT");
        if ( player.getScore() >= 1 )
        {
//            player.
            AnchorPane pane = FXMLLoader.load(getClass().getResource("CheckGame.fxml"));
            root.getChildren().setAll(pane);
        }
        else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    public void openPauseMenu() throws IOException {
        System.out.println("PAUSE BUTTON CLICKED");
        isPaused = true;
        FileOutputStream out = new FileOutputStream("isPaused.txt");
        out.write(49);
        out.close();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));
        root.getChildren().setAll(pane);
    }

    private void makeNewObjects()
    {
        ring = new Ring(225, 200, 0);
        cross = new Cross(165, -1000, 0);
        star1 = new Star(225, 400, false);
        star2 = new Star(225, 0, false);
        star3 = new Star(225, -400, false);
        star4 = new Star(225, -600, false);
        star5 = new Star(225, -1200, false);
        box = new Box(-600, 30);
        switchColour1 = new SwitchColour(225, 500, false);
        switchColour2 = new SwitchColour(225, -300, false);
        switchColour3 = new SwitchColour(225, -800, false);
        ball = new Ball(225, 700, -1);
        horizontal = new Horizontal(-200, 0);
    }

    private void deserializeObjects() throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream("ring.ser");
        ObjectInputStream os = new ObjectInputStream(fis);
        Ring r1 = (Ring) os.readObject();
        ring = new Ring(r1.getxCoordinate(), r1.getHeight(), r1.getAngle());
        System.out.println("Found ring, loading");

        fis = new FileInputStream("cross.ser");
        os = new ObjectInputStream(fis);
        Cross c1 = (Cross) os.readObject();
        cross = new Cross(c1.getxCoordinate(), c1.getHeight(), c1.getAngle());
        System.out.println("Found cross, loading");
        fis.close();

        fis = new FileInputStream("star1.ser");
        os = new ObjectInputStream(fis);
        Star s1 = (Star) os.readObject();
        star1 = new Star(s1.getxCoordinate(), s1.getHeight(), s1.getStatus());
        fis.close();
        fis = new FileInputStream("star2.ser");
        os = new ObjectInputStream(fis);
        s1 = (Star) os.readObject();
        star2 = new Star(s1.getxCoordinate(), s1.getHeight(), s1.getStatus());
        fis.close();
        fis = new FileInputStream("star3.ser");
        os = new ObjectInputStream(fis);
        s1 = (Star) os.readObject();
        star3 = new Star(s1.getxCoordinate(), s1.getHeight(), s1.getStatus());
        fis.close();
        fis = new FileInputStream("star4.ser");
        os = new ObjectInputStream(fis);
        s1 = (Star) os.readObject();
        star4 = new Star(s1.getxCoordinate(), s1.getHeight(), s1.getStatus());
        fis.close();
        fis = new FileInputStream("star5.ser");
        os = new ObjectInputStream(fis);
        s1 = (Star) os.readObject();
        star5 = new Star(s1.getxCoordinate(), s1.getHeight(), s1.getStatus());
        fis.close();
        System.out.println("Found stars, loading");

        fis = new FileInputStream("box.ser");
        os = new ObjectInputStream(fis);
        Box b1 = (Box) os.readObject();
        box = new Box(b1.getHeight(), b1.getAngle());
        System.out.println("Found box, loading");
        fis.close();
        fis = new FileInputStream("cs1.ser");
        os = new ObjectInputStream(fis);
        SwitchColour cs1 = (SwitchColour) os.readObject();
        switchColour1 = new SwitchColour(cs1.getxCoordinate(), cs1.getHeight(), cs1.getStatus());
        fis.close();
        fis = new FileInputStream("cs2.ser");
        os = new ObjectInputStream(fis);
        SwitchColour cs2 = (SwitchColour) os.readObject();
        switchColour2 = new SwitchColour(cs2.getxCoordinate(), cs2.getHeight(), cs2.getStatus());
        fis.close();
        fis = new FileInputStream("cs3.ser");
        os = new ObjectInputStream(fis);
        SwitchColour cs3 = (SwitchColour) os.readObject();
        switchColour3 = new SwitchColour(cs3.getxCoordinate(), cs3.getHeight(), cs3.getStatus());
        System.out.println("Found colour switchers, loading");
        fis.close();
        fis = new FileInputStream("ball.ser");
        os = new ObjectInputStream(fis);
        Ball ball1 = (Ball) os.readObject();
        ball = new Ball(ball1.getxCoordinate(), ball1.getHeight(), ball1.getColor());
        System.out.println("Found ball, loading");
        fis.close();
        fis = new FileInputStream("horizontal.ser");
        os = new ObjectInputStream(fis);
        Horizontal h1 = (Horizontal) os.readObject();
        horizontal = new Horizontal(h1.getHeight(), h1.getxCoordinate());
        System.out.println("Found horizontal bar, loading");
        fis.close();

    }

    private void serializeObjects() throws IOException{
        FileOutputStream fos = new FileOutputStream("player.ser");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(player);
        fos.close();

        fos = new FileOutputStream("cross.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(cross);
        fos.close();
        fos = new FileOutputStream("ring.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(ring);
        fos.close();
        fos = new FileOutputStream("horizontal.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(horizontal);
        fos.close();
        fos = new FileOutputStream("box.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(box);
        fos.close();
        fos = new FileOutputStream("ball.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(ball);
        fos.close();
        fos = new FileOutputStream("star1.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(star1);
        fos.close();
        fos = new FileOutputStream("star2.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(star2);
        fos.close();
        fos = new FileOutputStream("star3.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(star3);
        fos.close();
        fos = new FileOutputStream("star4.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(star4);
        fos.close();
        fos = new FileOutputStream("star5.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(star5);
        fos.close();
        fos = new FileOutputStream("ball.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(ball);
        fos.close();
        fos = new FileOutputStream("cs1.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(switchColour1);
        fos.close();
        fos = new FileOutputStream("cs2.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(switchColour2);
        fos.close();
        fos = new FileOutputStream("cs3.ser");
        os = new ObjectOutputStream(fos);
        os.writeObject(switchColour3);
        fos.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        primaryStage = new Stage();
        primaryStage.setTitle("MAIN GAME AREA");

        try {
            FileInputStream in = new FileInputStream("isNewGame.txt");
            if(in.read() == 48) {
                isNewGame = false;
                System.out.println("LOADING...........");
            }
            else {
                isNewGame = true;
                System.out.println("NEW GAME!!!");
            }

        } catch (IOException e) {
            isNewGame = true;
            FileOutputStream out = null;
            try {
                out = new FileOutputStream("isNewGame.txt");
                out.write(49);
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        isPaused = true;

        try
        {
            FileInputStream fis = new FileInputStream("player.ser");
            ObjectInputStream os = new ObjectInputStream(fis);
            player = (Player)os.readObject();
            System.out.println("Found player with score " + player.getTotalScore());
            player.stopPlaying();

            FileInputStream in = new FileInputStream("isPaused.txt");
            if(in.read() == 49) {
                isPaused = true;
            }
        }

        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("NO PLAYER FOUND/ERROR IN DESERIALIZING");
//            e.printStackTrace();
            player = new Player();
            player.stopPlaying();
        }

        if(!isNewGame)
        {
            try {
                deserializeObjects();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("NO object FOUND/ERROR IN DESERIALIZING");
//                e.printStackTrace();
                makeNewObjects();
                player.stopPlaying();
            }
        }

        else
        {
            makeNewObjects();
            player.clearCurrentScore();
            player.stopPlaying();
        }

        obstacles = new ArrayList<Touchable>();

        obstacles.add(star1);
        obstacles.add(star2);
        obstacles.add(star3);
        obstacles.add(star4);
        obstacles.add(star5);
        obstacles.add(ring);
        obstacles.add(horizontal);
        obstacles.add(box);
        obstacles.add(cross);
        obstacles.add(switchColour1);
        obstacles.add(switchColour2);
        obstacles.add(switchColour3);

        Text scoreDisplay = new Text("0");
        scoreDisplay.setFont(Font.font("MS Gothic", 33));
        scoreDisplay.setLayoutX(30);
        scoreDisplay.setLayoutY(50);
        scoreDisplay.setFill(Color.WHITE);

        Text godDisplay = new Text("G");
        godDisplay.setFont(Font.font("MS Gothic", 33));
        godDisplay.setLayoutX(90);
        godDisplay.setLayoutY(50);
        godDisplay.setFill(Color.rgb(27, 27, 27));

        root.getChildren().addAll(ball.getCircle(), star1.getStar(), star2.getStar(), star3.getStar(),
                star4.getStar(), star5.getStar(), scoreDisplay, godDisplay);

        ring.addToRoot(root);
        switchColour1.addToRoot(root);
        switchColour2.addToRoot(root);
        switchColour3.addToRoot(root);
        cross.addToRoot(root);
        horizontal.addToRoot(root);
        box.addToRoot(root);

        Scene newScene = new Scene(root, 450, 800, Color.rgb(27, 27, 27));

//        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode() == KeyCode.SPACE) {
//                ball.moveUp();
//                player.incrementTouch();
//                player.setPlaying();
//            }
//        });

        root.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if ( event.isPrimaryButtonDown()) {
                        ball.moveUp();
                        player.incrementTouch();
                        player.setPlaying();
                        isPaused = false;
                        try {
                            FileOutputStream out = new FileOutputStream("isPaused.txt");
                            out.write(48);
                            out.close();
                        } catch (Exception e) {
                            System.out.println("Pause error");
                        }
                    }
                }
        );

//        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode() == KeyCode.ESCAPE) {
////                player.stopPlaying();
//                isPaused = !isPaused;
//            }
//        });

        root.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if ( event.isSecondaryButtonDown()) {

                        try {
                            serializeObjects();
                        } catch(IOException e) {
                            System.out.println("ERROR IN SAVING");
                            e.printStackTrace();
                        }

                        isPaused = true;
                        try {
                            FileOutputStream out = new FileOutputStream("isPaused.txt");
                            out.write(49);
                            out.close();
                        } catch (Exception e) {
                            System.out.println("Pause error");
                        }

                        System.out.println("PAUSE BUTTON CLICKED");
                        AnchorPane pane = null;
                        try {
                            pane = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        root.getChildren().setAll(pane);
                    }
                }
        );

//        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode() == KeyCode.G) {
//                godMode = !godMode;
//                if(godMode) {
//                    godDisplay.setFill(Color.WHITE);
//                }
//                else {
//                    godDisplay.setFill(Color.rgb(27, 27, 27));
//                }
//            }
//        });

        root.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if (event.isMiddleButtonDown()) {
                        if (godMode == true) {
                            godMode = (false);
                        } else {
                            godMode = (true);
                        }

                        if (godMode) {
                            godDisplay.setFill(Color.WHITE);
                        } else {
                            godDisplay.setFill(Color.rgb(27, 27, 27));
                        }
                    }
                }
        );

//        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode() == KeyCode.S) {
//                try {
//                    serializeObjects();
//                } catch(IOException e) {
//                    System.out.println("ERROR IN SAVING");
//                    e.printStackTrace();
//                }
//            }
//        });


//        primaryStage.setScene(newScene);
//        primaryStage.show();

        new AnimationTimer() {
            long prevTime = 0;
            @Override
            public void handle(long now) {
                if(now - prevTime >= 3e7 && !isPaused) { // to get about 100 fps, idk why, but it works good

                    if(player.hasStarted()) {
                        ball.fall();
                    }

                    if(cross.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            stop();
                            loadgameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        isNewGame = true;
                    }

                    if(star1.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star1.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star2.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star2.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star3.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star3.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star4.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star4.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star5.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star5.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(ring.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            stop();
                            loadgameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        isNewGame = true;
                    }

                    if(switchColour1.checkCollision(ball)) {
                        System.out.println("Changing colour!");
                        switchColour1.collected();
                        ball.setColour(switchColour1.getRandomColour());
                    }

                    if(switchColour2.checkCollision(ball)) {
                        System.out.println("Changing colour!");
                        switchColour2.collected();
                        ball.setColour(switchColour2.getRandomColour());
                    }

                    if(switchColour3.checkCollision(ball)) {
                        System.out.println("Changing colour!");
                        switchColour3.collected();
                        ball.setColour(switchColour3.getRandomColour());
                    }

                    if(horizontal.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            stop();
                            loadgameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        isNewGame = true;
                    }

                    if(box.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            stop();
                            loadgameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        isNewGame = true;
                    }

                    if (ball.bottomTouched()) {
                        System.out.println("BOTTOM TOUCHED");
                        try {
                            stop();
                            loadgameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        isNewGame = true;
                    }

                    for(Touchable t:obstacles) {
                        t.checkOutOfFrame();
                    }

                    int dist = ball.aboveHalfScreen();
                    horizontal.move(new int[] {difficulty, dist}, 0);
//                    System.out.println(dist);
                    switchColour1.move(new int[] {0, dist}, 0);
                    switchColour2.move(new int[] {0, dist}, 0);
                    switchColour3.move(new int[] {0, dist}, 0);
                    box.move(new int[] {0, dist}, difficulty);
                    cross.move(new int[] {0, dist}, difficulty);
                    star1.move(new int[] {0, dist}, difficulty);
                    star2.move(new int[] {0, dist}, difficulty);
                    star3.move(new int[] {0, dist}, difficulty);
                    star4.move(new int[] {0, dist}, difficulty);
                    star5.move(new int[] {0, dist}, difficulty);
                    ring.move(new int[] {0, dist}, difficulty);

                    if(player.getScore() > 5) {
                        difficulty = -6;
                    }

                    if(player.getScore() > 10) {
                        difficulty = -9;
                    }

                    if(player.getScore() > 15) {
                        difficulty = -11;
                    }

                    prevTime = now;

                } else if (isPaused) {
//                    System.out.println("PAUSED");
                }
            }
        }.start(); // start animation timer

//        AnchorPane root = new AnchorPane();
/*        Player player = new Player(); // temporary; get player from memory, if not found then create new
        Cross cross = new Cross(285, -1000, 0);
        Ring ring = new Ring(225, 200, 0);
        Star star1 = new Star(225, 400);
        Star star2 = new Star(225, 0);
        Star star3 = new Star(225, -400);
        Star star4 = new Star(225, -800);
        Star star5 = new Star(225, -1200);
        Box box = new Box(-600, 30);
        SwitchColour switchColour1 = new SwitchColour(225, 500);
        SwitchColour switchColour2 = new SwitchColour(225, -300);
        SwitchColour switchColour3 = new SwitchColour(225, -1100);
        Ball ball = new Ball(225, 700, -1);
        Horizontal horizontal = new Horizontal(-200, 0);

        obstacles = new ArrayList<Touchable>();

        obstacles.add(star1);
        obstacles.add(star2);
        obstacles.add(star3);
        obstacles.add(star4);
        obstacles.add(star5);
        obstacles.add(ring);
        obstacles.add(horizontal);
        obstacles.add(box);
        obstacles.add(cross);
        obstacles.add(switchColour1);
        obstacles.add(switchColour2);
        obstacles.add(switchColour3);

        Text scoreDisplay = new Text("0");
        scoreDisplay.setFont(Font.font("MS Gothic", 33));
        scoreDisplay.setLayoutX(30);
        scoreDisplay.setLayoutY(50);
        scoreDisplay.setFill(Color.WHITE);

        Text godDisplay = new Text("G");
        godDisplay.setFont(Font.font("MS Gothic", 33));
        godDisplay.setLayoutX(90);
        godDisplay.setLayoutY(50);
        godDisplay.setFill(Color.rgb(27, 27, 27));

        root.getChildren().addAll(ball.getCircle(), star1.getStar(), star2.getStar(), star3.getStar(),
                star4.getStar(), star5.getStar(), scoreDisplay, godDisplay);

        ring.addToRoot(root);
        switchColour1.addToRoot(root);
        switchColour2.addToRoot(root);
        switchColour3.addToRoot(root);
        cross.addToRoot(root);
        horizontal.addToRoot(root);
        box.addToRoot(root);

        Scene newScene = new Scene(root, 450, 800, Color.rgb(27, 27, 27));
*/
//        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode() == KeyCode.SPACE) {
//                ball.moveUp();
//                player.incrementTouch();
//                player.setPlaying();
//            }
//        });

/*        root.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if ( event.isPrimaryButtonDown()) {
                        ball.moveUp();
                        player.incrementTouch();
                        player.setPlaying();
                    }
                }
        );
*/
//        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode() == KeyCode.ESCAPE) {
////                player.stopPlaying();
//                isPaused = !isPaused;
//            }
//        });
/*
        root.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if ( event.isSecondaryButtonDown()) {
                        boolean pause = isPaused ;
                        //                  player.stopPlaying();
                        if (pause) {
                            isPaused = false;
                        } else {
                            isPaused = true;
                        }
                    }
                }
        );

*/

//        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if(key.getCode() == KeyCode.G) {
//                godMode = !godMode;
//                if(godMode) {
//                    godDisplay.setFill(Color.WHITE);
//                }
//                else {
//                    godDisplay.setFill(Color.rgb(27, 27, 27));
//                }
//            }
//        });

 /*       root.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if (event.isMiddleButtonDown()) {
                        if (godMode == true) {
                            godMode = (false);
                        } else {
                            godMode = (true);
                        }

                        if (godMode) {
                            godDisplay.setFill(Color.WHITE);
                        } else {
                            godDisplay.setFill(Color.rgb(27, 27, 27));
                        }
                    }
                }
        );
*/
//
//        primaryStage.setScene(newScene);
//        primaryStage.show();

 /*       new AnimationTimer() {
            long prevTime = 0;
            @Override
            public void handle(long now) {
                if(now - prevTime >= 3e7 && !isPaused) { // to get about 100 fps, idk why, but it works good

                    if(player.hasStarted()) {
                        ball.fall();
                    }

                    if(cross.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            gameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stop();
                    }

                    if(star1.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star1.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star2.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star2.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star3.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star3.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star4.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star4.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(star5.checkCollision(ball)) {
                        System.out.println("Star collected!");
                        star5.collected();
                        player.incrementScore();
                        scoreDisplay.setText(Integer.toString(player.getScore()));
                    }

                    if(ring.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            gameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stop();
                    }

                    if(switchColour1.checkCollision(ball)) {
                        System.out.println("Changing colour!");
                        switchColour1.collected();
                        ball.setColour(switchColour1.getRandomColour());
                    }

                    if(switchColour2.checkCollision(ball)) {
                        System.out.println("Changing colour!");
                        switchColour2.collected();
                        ball.setColour(switchColour2.getRandomColour());
                    }

                    if(switchColour3.checkCollision(ball)) {
                        System.out.println("Changing colour!");
                        switchColour3.collected();
                        ball.setColour(switchColour3.getRandomColour());
                    }

                    if(horizontal.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            gameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stop();
                    }

                    if(box.checkCollision(ball) && !godMode) {
                        System.out.println("Intersection!");
                        try {
                            gameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stop();
                    }

                    if (ball.bottomTouched()) {
                        System.out.println("BOTTOM TOUCHED");
                        try {
                            gameOver();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stop();
                    }

                    for(Touchable t:obstacles) {
                        t.checkOutOfFrame();
                    }

                    int dist = ball.aboveHalfScreen();
                    horizontal.move(new int[] {-4, dist}, 0);
//                    System.out.println(dist);
                    switchColour1.move(new int[] {0, dist}, 0);
                    switchColour2.move(new int[] {0, dist}, 0);
                    switchColour3.move(new int[] {0, dist}, 0);
                    box.move(new int[] {0, dist}, 4);
                    cross.move(new int[] {0, dist}, 4);
                    star1.move(new int[] {0, dist}, 4);
                    star2.move(new int[] {0, dist}, 4);
                    star3.move(new int[] {0, dist}, 4);
                    star4.move(new int[] {0, dist}, 4);
                    star5.move(new int[] {0, dist}, 4);
                    ring.move(new int[] {0, dist}, 4);


                    prevTime = now;

                } else if (isPaused) {
//                    System.out.println("PAUSED");
                }
            }
        }.start(); // start animation timer
*/
    }
}