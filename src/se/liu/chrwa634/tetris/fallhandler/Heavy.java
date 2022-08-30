package se.liu.chrwa634.tetris.fallhandler;

import se.liu.chrwa634.tetris.Board;
import se.liu.chrwa634.tetris.SquareType;

public class Heavy extends AbstractFallHandler
{
    /**
     * This method check if a collsion has occured. If the falling polyimino has not falled down one step than use the code
     * in the abstract class AbstractFallHandlder. If not check if blocks could be pushed down to make space for the heavy polyiomino.
     * If there are not collision than this function would automatically push block down to make space for the heavy polyomino.
     * @param xMoved The direction the polyiomino moves in the x direction.
     * @param yMoved The direction the polyiomino moves in the y direction.
     * @param board The board in which to check on.
     * @return true if a collision happens otherwise false.
     */
    @Override public boolean hasCollision(final int xMoved, final int yMoved, final Board board) {
	if (yMoved == 0) {
	    return super.hasCollision(xMoved, yMoved, board);
	}

	initiateVariables(board);

	for (int y = 0; y < polyHeight; y++) {
	    for (int x = 0; x < polyWidth; x++) {
		// If the poly block is an empty square than we do not need to check it.
	        if (!poly.getBlock(x, y).equals(SquareType.EMPTY)) {
		    SquareType boardSquare = board.getBlock(polyX + x + xMoved + margin, polyY + y + yMoved + margin);
		    // If the square on the board which we are going to move to is empty then we do not need to check that either.
		    if (!boardSquare.equals(SquareType.EMPTY)) {

			// If the square is an outside square than we have collided.
	                if (boardSquare.equals(SquareType.OUTSIDE)) {
			    return true;
			}

			// Find an empty square between the poly block and the outside square of the board.
			int offset = 0;
			while (true) {
			    SquareType blockToCheck = board.getBlock(polyX + x + xMoved + margin, polyY + y + yMoved + margin + offset);
			    // If we hit the outside block that there cannot be a empty square between the polyomino and
			    // the edge of the board, which means there will be a collision.
			    if (blockToCheck.equals(SquareType.OUTSIDE)) {
			        return true;
			    }
			    //If a empty square is found then there is no collision because the blocks could be moved down.
			    else if (blockToCheck.equals(SquareType.EMPTY)) {
			        break;
			    }
			    offset++;
			}
		    }
		}
	    }
	}
	//When there is no collision then we move down the squares on the board.
	moveDownSquares(board);
	return false;
    }

    /**
     * This function returns the name of the FallHandler.
     * @return Name of FallHandler as a string.
     */
    @Override public String getName() {
	return "Heavy";
    }

    /**
     * This method moves down the blocks under the polyomino. The method assumes it is possible to do that.
     * @param board The board in which the blocks would be pushed down.
     */
    private void moveDownSquares(Board board) {
	for (int x = 0; x < polyWidth; x++) {
	    for (int y = 0; y < polyHeight; y++) {
		SquareType polyBlock = poly.getBlock(x, y);
		//Check if the poly at the position is an actual block and not empty.
		if (!polyBlock.equals(SquareType.EMPTY)) {
		    int offset = 1;
		    //Find the empty blocks offset to move the squares on top of it to.
		    while (true) {
			SquareType boardBlock = board.getBlock(polyX + x + margin, polyY + y + offset + margin);
			if (boardBlock.equals(SquareType.EMPTY)) {
			    break;
			}
			offset++;
		    }

		    //Decrease by one to get to the block and not the empty square.
		    offset--;

		    while (offset >= 1) {
			SquareType boardSquare = board.getBlock(polyX + x + margin, polyY + offset + y + margin);
			//remove the previous block that have been taken.
			board.setBlock(polyX + x + margin, polyY + offset + y + margin, SquareType.EMPTY);

			//place the block one under.
			board.setBlock(polyX + x + margin, polyY + offset + 1 + y + margin, boardSquare);

			offset--;
		    }
		}
	    }
	}
    }
}
