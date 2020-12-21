import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public class Arch extends Item implements Serializable {

    private Arc arc1, arc2;
    private Shape arc;

    public Arch(int x, int y, int a, int rad, boolean isObstacle) {

        int delta = 10;
        this.angle = 0;
        if(!isObstacle)
            delta = rad;

        arc1 = new Arc();
        arc1.setCenterX(x);
        arc1.setCenterY(y);
        arc1.setRadiusX(rad);
        arc1.setRadiusY(rad);
        arc1.setStartAngle(a);
        arc1.setLength(90);
        arc1.setType(ArcType.ROUND);

        arc2 = new Arc();
        arc2.setCenterX(x);
        arc2.setCenterY(y);
        arc2.setRadiusX(rad-delta);
        arc2.setRadiusY(rad-delta);
        arc2.setStartAngle(a);
        arc2.setLength(90);
        arc2.setType(ArcType.ROUND);

        arc = Shape.subtract(arc1, arc2);

        if (a == 0) {
            arc.setFill(Color.rgb(147, 17, 245)); //purple
            colour = 0;
        } else if (a == 90) {
            arc.setFill(Color.rgb(53, 225, 243)); //blue
            colour = 1;
        } else if (a == 180) {
            arc.setFill(Color.rgb(254, 0, 124)); // pink
            colour = 2;
        } else {
            arc.setFill(Color.rgb(245, 223, 18)); // yellow
            colour = 3;
        }

        height = y;
        xCoordinate = x;

    }

    public void setArcColours(int c) {
        if (c == 0) {
            arc.setFill(Color.rgb(147, 17, 245)); //purple
            colour = 0;
        } else if (c == 1) {
            arc.setFill(Color.rgb(53, 225, 243)); //blue
            colour = 1;
        } else if (c == 2) {
            arc.setFill(Color.rgb(254, 0, 124)); // pink
            colour = 2;
        } else {
            arc.setFill(Color.rgb(245, 223, 18)); // yellow
            colour = 3;
        }
    }

    public Shape getArc() {
        return arc;
    }

    @Override
    public void move(int[] pos, int angle) {
        int delx = pos[0];
        int dely = pos[1];
        this.angle += angle;
        this.angle%= 360;

        Rotate rotate = new Rotate();
        rotate.setAngle(angle);
        rotate.setPivotX(xCoordinate);
        rotate.setPivotY(height);
        arc.getTransforms().add(rotate);

        arc.setLayoutX(arc.getLayoutX()+delx);
        arc.setLayoutY(arc.getLayoutY()+dely);
    }

    public void shift(int h) {
        arc.setLayoutY(arc.getLayoutY()+h);
    }

    public boolean checkCollision(Ball ball) {
        return (Shape.intersect(ball.getCircle(), arc).getLayoutBounds().getMinX() != 0) && (ball.getColor() != this.colour);
    }
}
