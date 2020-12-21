import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Star extends Item implements Touchable, Serializable {

    private transient Image star;
    private transient ImageView starView;
    private boolean isCollected;
    private static final long serialversionUID = 12934893L;

    public Star(int x, int y, boolean status) {
//        star = new Image("white-star.png");
        Image image = new Image(getClass().getResourceAsStream("assets/white-star.png"));
        starView = new ImageView(image);

        starView.setFitHeight(30);
        starView.setFitWidth(30);
        starView.setPreserveRatio(true);
        starView.setLayoutX(x-15);
        starView.setLayoutY(y-15);
        xCoordinate = x;
        height = y;
        isCollected = status;
        if(isCollected) {
            starView.setVisible(false);
        }
    }

    @Override
    public void move(int[] pos, int angle) {
        starView.setLayoutY(starView.getLayoutY()+pos[1]);
        height += pos[1];
    }

    private void shift() {
        starView.setLayoutY(starView.getLayoutY()-2000);
    }

    public ImageView getStar() {
        return starView;
    }

    @Override
    public boolean checkCollision(Ball ball) {
        return (height+30 - (ball.getCircle().getCenterY()-12) > 0 && !this.isCollected);
    }

    @Override
    public void checkOutOfFrame() {
        if(height-15 > 800) {
            height -= 2000;
            shift();
            respawn();
        }
    }

    public void collected() {
        starView.setVisible(false);
        isCollected = true;
    }

    private void respawn() {
        starView.setVisible(true);
        isCollected = false;
    }

    public boolean getStatus() {
        return isCollected;
    }

}
