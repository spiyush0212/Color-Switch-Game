import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public class Line extends Item implements Touchable, Serializable {

    private Rectangle rectangle;

    public Line(int x, int y, int a) {
        super();
        angle = 0;

        rectangle = new Rectangle(x, y, 100, 10);

//        rectangle.setFill(Color.WHITE);

        Rotate rotate = new Rotate();
        rotate.setAngle(a);
        rotate.setPivotX(x);
        rotate.setPivotY(y);
        rectangle.getTransforms().add(rotate);

        xCoordinate = x;
        height = y;

        if(a == 0) {
            rectangle.setFill(Color.rgb(147, 17, 245)); //purple
            rectangle.setY(rectangle.getY()-5);
            colour = 0;
        } else if (a == 90) {
            rectangle.setFill(Color.rgb(53, 225, 243)); //blue
            rectangle.setY(rectangle.getY()-5);
            colour = 1;
        } else if (a == 180) {
            rectangle.setFill(Color.rgb(254, 0, 124)); //pink
            rectangle.setY(rectangle.getY()-5);
            colour = 2;
        } else {
            rectangle.setFill(Color.rgb(245, 223, 18)); //yellow
            rectangle.setY(rectangle.getY()-5);
            colour = 3;
        }

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void move(int[] pos, int angle) {

        int delx = pos[0];
        int dely = pos[1];

        Rotate rotate = new Rotate();
        rotate.setAngle(this.angle + angle);
        rotate.setPivotX(xCoordinate);
        rotate.setPivotY(height);
        rectangle.getTransforms().add(rotate);

        rectangle.setLayoutX(rectangle.getLayoutX()+delx);
        rectangle.setLayoutY(rectangle.getLayoutY()+dely);

    }

    public void shift(int h) {
        rectangle.setLayoutY(rectangle.getLayoutY()+h);
    }

    public void rotate(int angle) {
        Rotate rotate = new Rotate();
        rotate.setAngle(this.angle + angle);
        rotate.setPivotX(xCoordinate);
        rotate.setPivotY(height);
        rectangle.getTransforms().add(rotate);
    }

    @Override
    public boolean checkCollision(Ball ball) {
        return (Shape.intersect(ball.getCircle(), rectangle).getLayoutBounds().getMinX() != 0) && (ball.getColor() != this.colour);
    }
}
