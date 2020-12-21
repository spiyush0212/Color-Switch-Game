
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;


public class Cross extends Item implements Touchable, Serializable {

    private transient ArrayList<Line> lineList;
    private static final long serialversionUID = 129348L;

    public Cross(int x, int y, int angle) {

        lineList = new ArrayList<Line>();

        for(int i=0; i<4; i++) {
            lineList.add(new Line(x, y, 90*i));
        }
        xCoordinate = x;
        height = y;
        this.angle = angle;

        move(new int[] {0,0}, angle);
    }

    @Override
    public void move(int[] pos, int angle) {

        for (Line l:lineList) {
            l.move(pos, angle);
        }
        this.angle += angle;
        this.angle %= 360;
        height += pos[1];
    }

    private void shift() {
        for (Line l:lineList) {
            l.shift(-2000);
        }
    }

    public void addToRoot(AnchorPane root) {
        for(Line l:lineList) {
            root.getChildren().add(l.getRectangle());
        }
    }

    @Override
    public boolean checkCollision(Ball ball) {

        boolean ans = false;
        for(Line l:lineList) {
            ans = ans || l.checkCollision(ball);
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
