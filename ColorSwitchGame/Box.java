import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public class Box extends Item implements Touchable, Serializable {

    private transient Rectangle r1, r2, r3, r4;
    private int colour[];

    public Box(int y, int angle) {
        height = y;
        r1 = new Rectangle();
        r2 = new Rectangle();
        r3 = new Rectangle();
        r4 = new Rectangle();

        r1.setLayoutX(225-105);
        r1.setLayoutY(y-105);
        r1.setWidth(200);
        r1.setHeight(10);

        r2.setLayoutX(225+100);
        r2.setLayoutY(y-100);
        r2.setWidth(200);
        r2.setHeight(10);

        r3.setLayoutX(225-105);
        r3.setLayoutY(y+95);
        r3.setWidth(200);
        r3.setHeight(10);

        r4.setLayoutX(225-100);
        r4.setLayoutY(y-100);
        r4.setWidth(200);
        r4.setHeight(10);

        this.angle = angle;

        Rotate rotate = new Rotate();
        rotate.setAngle(90);
        r2.getTransforms().add(rotate);
        r4.getTransforms().add(rotate);

        colour = new int[] {0,1,2,3};
        r1.setFill(Color.rgb(147, 17, 245));
        r2.setFill(Color.rgb(53, 225, 243));
        r3.setFill(Color.rgb(254, 0, 124));
        r4.setFill(Color.rgb(245, 223, 18));

        move(new int[]{0,0}, angle);
    }

    public void addToRoot(AnchorPane root) {
        root.getChildren().addAll(r1, r2, r3, r4);
    }

    @Override
    public void move(int[] pos, int angle) {

        r1.setLayoutY(r1.getLayoutY()+ pos[1]);
        r2.setLayoutY(r2.getLayoutY()+ pos[1]);
        r3.setLayoutY(r3.getLayoutY()+ pos[1]);
        r4.setLayoutY(r4.getLayoutY()+ pos[1]);
        height += pos[1];
        this.angle += angle;
        this.angle %= 360;

        Rotate rotate = new Rotate();
        rotate.setAngle(angle);
        rotate.setPivotX(100);
        rotate.setPivotY(105);
        r1.getTransforms().add(rotate);
        r2.getTransforms().add(rotate);

        rotate = new Rotate();
        rotate.setAngle(angle);
        rotate.setPivotX(100);
        rotate.setPivotY(-95);
        r3.getTransforms().add(rotate);
        r4.getTransforms().add(rotate);

    }

    private void shift() {
        int delta = -2000;
        r1.setLayoutY(r1.getLayoutY()+ delta);
        r2.setLayoutY(r2.getLayoutY()+ delta);
        r3.setLayoutY(r3.getLayoutY()+ delta);
        r4.setLayoutY(r4.getLayoutY()+ delta);
    }

    @Override
    public boolean checkCollision(Ball ball) {
        boolean ans = false;
        ans = ans || (Shape.intersect(ball.getCircle(), r1).getLayoutBounds().getMinX() != 0) && (ball.getColor() != colour[0]);
        ans = ans || (Shape.intersect(ball.getCircle(), r2).getLayoutBounds().getMinX() != 0) && (ball.getColor() != colour[1]);
        ans = ans || (Shape.intersect(ball.getCircle(), r3).getLayoutBounds().getMinX() != 0) && (ball.getColor() != colour[2]);
        ans = ans || (Shape.intersect(ball.getCircle(), r4).getLayoutBounds().getMinX() != 0) && (ball.getColor() != colour[3]);
        return ans;
    }

    @Override
    public void checkOutOfFrame() {
        if(height - 55 > 800) {
            height -= 2000;
            shift();
        }
    }
}
