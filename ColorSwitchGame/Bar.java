import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.Serializable;

public class Bar extends Item implements Touchable, Serializable {

    private Rectangle rectangle;
    public Bar(double x, int y, int c) {
        height = y;
        xCoordinate = (int)x;
        rectangle = new Rectangle();
        rectangle.setLayoutX(x+10);
        rectangle.setLayoutY(y);
        rectangle.setWidth(112);
        rectangle.setHeight(10);
        if(c == 0) {
            rectangle.setFill(Color.rgb(147, 17, 245)); //purple
            colour = 0;
        } else if (c == 1) {
            rectangle.setFill(Color.rgb(53, 225, 243)); //blue
            colour = 1;
        } else if (c == 2) {
            rectangle.setFill(Color.rgb(254, 0, 124)); //pink
            colour = 2;
        } else {
            rectangle.setFill(Color.rgb(245, 223, 18)); //yellow
            colour = 3;
        }
    }

    @Override
    public boolean checkCollision(Ball ball) {
        return (Shape.intersect(rectangle, ball.getCircle()).getLayoutBounds().getMinX() != 0) && (ball.getColor() != this.colour);
    }

    @Override
    public void move(int[] pos, int angle) {
        height += pos[1];
        xCoordinate = (int) (rectangle.getLayoutX() + pos[0]);
        if(xCoordinate <= -112) {
            xCoordinate = 448+112;
        }
        rectangle.setLayoutY(height);
        rectangle.setLayoutX(xCoordinate);
    }

    public void shift(int h) {
        height = h;
        rectangle.setLayoutY(h);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
