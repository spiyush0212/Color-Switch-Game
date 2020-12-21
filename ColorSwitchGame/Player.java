import java.io.Serializable;

public class Player implements Serializable {
    private int score;
    private int totalScore;
    private int totalScreenTouches;
    private int highscore;
    private boolean isPlaying;
    private static final long serialversionUID = 129348938L;

    public Player() {
        score = 0;
        totalScore = 0;
        totalScreenTouches = 0;
        isPlaying = false;
        highscore = 0;
    }

    public void incrementScore() {
        score += 1;
        totalScore += 1;
        if(score > highscore) {
            highscore = score;
        }
    }

    public void setPlaying() {
        isPlaying = true;
    }

    public void stopPlaying() {
        isPlaying = false;
    }

    public boolean hasStarted() {
        return isPlaying;
    }

    public void incrementTouch() {
        totalScreenTouches++;
    }

    public int getScore() {
        return score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void clearCurrentScore() {
        score = 0;
        isPlaying = false;
    }

    public int getTotalScreenTouches() {
        return totalScreenTouches;
    }

    public int getHighscore() {
        return highscore;
    }
}
