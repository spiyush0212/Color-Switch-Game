public interface Touchable {
    public boolean checkCollision(Ball ball);
    default void checkOutOfFrame() {

    }
}
