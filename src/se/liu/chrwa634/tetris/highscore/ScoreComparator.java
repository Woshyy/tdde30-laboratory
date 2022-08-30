package se.liu.chrwa634.tetris.highscore;

import java.util.Comparator;

/**
 * This class implements a way to compare to different highscores. It is by observing the different values of the highscore.
 */
public class ScoreComparator implements Comparator<Highscore>
{
    /**
     * This method compares which highscore is higher.
     * @param highscore1 A highscore.
     * @param highscore2 A highscore.
     * @return 0 if they are both identical, <0 if the second higscore is larger, >0 if the first highscore is larger.
     */
    @Override public int compare(Highscore highscore1, Highscore highscore2) {
        int score1 = highscore1.getHighscore();
        int score2 = highscore2.getHighscore();
	return Integer.compare(score2, score1);
    }
}
