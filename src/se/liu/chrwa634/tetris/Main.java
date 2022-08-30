package se.liu.chrwa634.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main
{
    public static final int GAME_HEIGHT = 20;
    public static final int GAME_WIDTH = 10;
    public static final int START_DELAY = 500;
    public static final int SPEED_UP_RATE_TICK = 20000;
    public static final int SPEED_UP_RATE_AMOUNT = 20;
    public static final int SPEED_UP_LIMIT = 20;

    private int tickCount = 0;

    public static void main(String[] args) {
	Board board = new Board(GAME_HEIGHT, GAME_WIDTH);

	StartScreen.show();
	TetrisViewer.show(board);


	final Action doOneStep = new AbstractAction()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
	        board.tick();

		//Reset the timer at game over incase that the player wants to play again.
		if (board.getGameOver()) {
		    Timer clockTimer = (Timer) e.getSource();
		    clockTimer.setDelay(START_DELAY);
		}
	    }
	};


	final Timer clockTimer = new Timer(START_DELAY, doOneStep);


	final Action increaseTickRate = new AbstractAction()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		// If the game is not over, on pause and not have reached the maximum speed than decrease the time between ticks.
		if (!board.getGameOver() &&
		    !board.getPause() &&
		    clockTimer.getDelay() > SPEED_UP_LIMIT) {
		    clockTimer.setDelay(clockTimer.getDelay() - SPEED_UP_RATE_AMOUNT);
		}
	    }
	};

	final Timer speedUpTimer = new Timer(SPEED_UP_RATE_TICK, increaseTickRate);

	speedUpTimer.setCoalesce(true);
	clockTimer.setCoalesce(true);

	clockTimer.start();
	speedUpTimer.start();
    }


}
