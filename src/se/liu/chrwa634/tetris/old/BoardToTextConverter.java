package se.liu.chrwa634.tetris.old;

import se.liu.chrwa634.tetris.Board;
import se.liu.chrwa634.tetris.Poly;

public class BoardToTextConverter
{
    public static String convertToText(Board board) {
        StringBuilder builder = new StringBuilder();
        Poly currentFallingPoly = board.getFalling();

	for (int y = 0; y < board.getHeight(); y++) {
	    for (int x = 0; x < board.getWidth(); x++) {

		if (isCoordinateInFallingPoly(board, x, y)) {
		    switch(currentFallingPoly.getBlock(x - currentFallingPoly.getX(), y - currentFallingPoly.getY())) {
			case EMPTY:
			    builder.append(" ");
			    break;
			case I:
			    builder.append("#");
			    break;
			case J:
			    builder.append("+");
			    break;
			case O:
			    builder.append("%");
			    break;
			case S:
			    builder.append("$");
			    break;
			case T:
			    builder.append("O");
			    break;
			case Z:
			    builder.append("0");
			    break;
			case L:
			    builder.append("@");
			    break;
		    }
		}
		else {
		    switch(board.getBlock(x, y)) {
			case EMPTY:
			    builder.append(" ");
			    break;
			case I:
			    builder.append("#");
			    break;
			case J:
			    builder.append("@");
			    break;
			case O:
			    builder.append("%");
			    break;
			case S:
			    builder.append("$");
			    break;
			case T:
			    builder.append("O");
			    break;
			case Z:
			    builder.append("0");
			    break;
			case L:
			    builder.append("+");
		    }
		}

	    }
	    builder.append("\n");
	}
	return builder.toString();
    }
    private static boolean isCoordinateInFallingPoly(Board board, int x, int y) {
	Poly currentFallingPoly = board.getFalling();
	int currentFallingPolyX = currentFallingPoly.getX();
	int currentFallingPolyY = currentFallingPoly.getY();
	int height = currentFallingPoly.getHeight();
	int width = currentFallingPoly.getWidth();

	//if the coordinates is inside the poly then return true
	if (currentFallingPolyX <= x && (currentFallingPolyX + height) > x) {
	    if (currentFallingPolyY <= y && (currentFallingPolyY + height) > y) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	else {
	    return false;
	}
    }
}
