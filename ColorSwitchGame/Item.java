import java.io.Serializable;

public abstract class Item implements Serializable {
    protected int colour;
    protected int height;
    protected int xCoordinate;
    protected int angle;

    public int getHeight() {
        return height;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getAngle() {
        return angle;
    }

    public abstract void move(int[] pos, int angle);

    protected void setColour(int colour) {
        this.colour = colour;
    }

    protected int getColor() {
        return colour;
    }
}
