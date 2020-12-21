import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class Horizontal extends Item implements Touchable, Serializable {

    private transient ArrayList<Bar> bars;
    private static final long serialversionUID = 1293489L;

    public Horizontal(int y, int savedX) {

        bars = new ArrayList<>();
        height = y;
        xCoordinate = savedX;
        angle = 0;
        for(int i=0; i<6; i++) {
            bars.add(new Bar((double)(i-1)*(450/4), y, i%4));
        }
//        move(new int[] {xCoordinate, 0}, 0);
    }
    public void addToRoot(AnchorPane root) {
        for(Bar r:bars) {
            root.getChildren().add(r.getRectangle());
        }
    }

    @Override
    public void move(int[] pos, int angle) {
        height += pos[1];
        xCoordinate += pos[0];
        for(Bar r:bars) {
            r.move(pos, angle);
        }
//        System.out.println(height);
    }

    private void shift() {
        for(Bar r:bars) {
            r.shift(height);
        }
    }

    @Override
    public boolean checkCollision(Ball ball) {
        boolean ans = false;
        for(Bar b:bars) {
            ans = ans || b.checkCollision(ball);
        }
        return ans;
    }

    @Override
    public void checkOutOfFrame() {
        if(height > 800) {
            height -= 2000;
            shift();
        }
    }
}
