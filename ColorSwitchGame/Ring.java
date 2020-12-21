import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class Ring extends Item implements Touchable, Serializable {

    private transient ArrayList<Arch> archList;
    private static final long serialversionUID = 12934L;

    public Ring(int x, int y, int angle) {
        height = y;
        xCoordinate = x;
        archList = new ArrayList<Arch>();

        for(int i=0; i<4; i++) {
            archList.add(new Arch(x, y, 90*i, 110, true));
        }
        colour = -1;
        this.angle = angle;
        move(new int[] {0, 0}, angle);
    }

    public void addToRoot(AnchorPane root) {
        for(Arch a:archList) {
            root.getChildren().add(a.getArc());
        }
    }

    @Override
    public void move(int[] pos, int angle) {
        height += pos[1];
        int dely = pos[1];
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

    @Override
    public boolean checkCollision(Ball ball) {
        boolean ans = false;
        for(Arch a:archList) {
            ans = ans || a.checkCollision(ball);
        }
        return ans;
    }

    @Override
    public void checkOutOfFrame() {
        if(height-75 > 800) {
            height -= 2000;
            shift();
//            System.out.println("SHIFTING RING");
        }
    }
}
