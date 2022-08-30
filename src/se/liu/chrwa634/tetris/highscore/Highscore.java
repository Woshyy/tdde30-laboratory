package se.liu.chrwa634.tetris.highscore;

/**
 * This class represent a highscore. It saves the highscore as an integer and the player that set the highscore as a string.
 */
public class Highscore
{
    private int highScore;
    private String name;

    public Highscore(int highScore, String name) {
        this.highScore = highScore;
        this.name = name;
    }

    /**
     * Get the name of the player that set the highscore.
     * @return The player's name as a string.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve the Highscore as an integer.
     * @return An integer representing the highscore.
     */
    public int getHighscore() {
        return highScore;
    }

    @Override public String toString() {
        return name + " = " + highScore;
    }
}
