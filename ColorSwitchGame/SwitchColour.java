import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class SwitchColour extends Item implements Touchable, Serializable {

    private transient ArrayList<Arch> archList;
    private boolean isCollected;
    Random random;
    private static final long serialversionUID = 129123123L;

    public SwitchColour(int x, int y, boolean status) {
        archList = new ArrayList<Arch>();
        height = y;
        xCoordinate = x;

        for(int i=0; i<4; i++) {
            archList.add(new Arch(x, y, 90*i, 15,false));
        }

        colour = -1;
        angle = 0;
        isCollected = status;

        random = new Random();
    }

    public boolean getStatus() {
        return isCollected;
    }

    public void addToRoot(AnchorPane root) {
        for(Arch a:archList) {
            root.getChildren().add(a.getArc());
        }
    }

    @Override
    public void move(int[] pos, int angle) {
        height += pos[1];
        for(Arch a:archList) {
            a.move(pos, angle);
        }
        this.angle += angle;
        this.angle %= 360;
    }

    private void shift() {
        for(Arch a:archList) {
            a.shift(-2000);
        }
    }

    public void collected() {
        this.isCollected = true;
        for(Arch a:archList) {
            a.getArc().setFill(Color.TRANSPARENT);
        }
    }

    private void respawn() {
        this.isCollected = false;
        for(int i=0; i<archList.size(); i++) {
            archList.get(i).setArcColours(i);
        }
    }

    @Override
    public boolean checkCollision(Ball ball) {
        boolean ans = false;
        for(Arch a:archList) {
            ans = ans || a.checkCollision(ball);
        }
        ans = ans && !isCollected;
        return ans;
    }

    @Override
    public void checkOutOfFrame()  {
        if(height > 800) {
            height -= 2000;
            shift();
            respawn();
        }
    }

    public int getRandomColour() {
        return random.nextInt(4);
    }

    public void printheight() {
        System.out.println(height);
    }
}
