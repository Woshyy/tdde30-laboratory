package se.liu.chrwa634.tetris.fallhandler;

import se.liu.chrwa634.tetris.Board;
import se.liu.chrwa634.tetris.SquareType;

public class Fallthrough extends AbstractFallHandler
{
    @Override public boolean hasCollision(int xMoved, int yMoved, Board board) {
        initiateVariables(board);

	for (int y = 0; y < polyHeight; y++) {
	    for (int x = 0; x < polyWidth; x++) {
		if (!poly.getBlock(x, y).equals(SquareType.EMPTY)) {
		    if (board.getBlock(x + polyX + xMoved + margin, y + polyY + yMoved + margin).equals(SquareType.OUTSIDE)) {
			return true;
		    }
		}
	    }
	}
	return false;
    }

    @Override public String getName() {
	return "Fallthrough";
    }
}
