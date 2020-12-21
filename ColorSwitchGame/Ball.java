import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Ball extends Item implements Serializable {

    private transient Circle ball;
    private int velocity;
    private static final long serialversionUID = 1293L;

    public Ball(int x, int y, int colour) {
        this.colour = colour;
        ball = new Circle();
        ball.setCenterX(x);
        ball.setCenterY(y);
        ball.setRadius(12);
        velocity = 0;
        xCoordinate = x;
        height = y;
        setColour(colour);
    }

    @Override
    public void move(int[] pos, int angle) {

    }

    public void moveUp() {
        velocity += -110;
    }

    public void fall() {
        velocity += 3;
        if(velocity < -20)
            velocity = -20; //max upward velocity
        if(velocity > 20)
            velocity = 20;  //max downward velocity
        ball.setCenterY(ball.getCenterY() + velocity);
        height = (int)ball.getCenterY() + velocity;
    }

    public void setColour(int colour) {
        super.setColour(colour);
        if(colour == -1) {
            ball.setFill(Color.WHITE);
        } else if (colour == 0) {
            ball.setFill(Color.rgb(147, 17, 245)); //purple
        } else if (colour == 1) {
            ball.setFill(Color.rgb(53, 225, 243)); //blue
        } else if (colour == 2) {
            ball.setFill(Color.rgb(254, 0, 124)); // pink
        } else if (colour == 3) {
            ball.setFill(Color.rgb(245, 223, 18)); // yellow
        }
    }

    public Circle getCircle() {
        return ball;
    }

    public boolean bottomTouched() {
        if(ball.getCenterY() > 780) {
            ball.setCenterY(800);
            return true;
        }
        return false;
    }

    public int aboveHalfScreen() {
        if(ball.getCenterY() + velocity < 350) {
            ball.setCenterY(350);
            return (int)(350 - (ball.getCenterY() + velocity));
        }
        return 0;
    }

}
