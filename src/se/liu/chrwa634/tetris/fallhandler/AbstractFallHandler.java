package se.liu.chrwa634.tetris.fallhandler;

import se.liu.chrwa634.tetris.Board;
import se.liu.chrwa634.tetris.Poly;
import se.liu.chrwa634.tetris.SquareType;

public abstract class AbstractFallHandler implements FallHandler
{
    protected Poly poly = null;
    protected int polyY;
    protected int polyX;
    protected int polyHeight;
    protected int polyWidth;
    protected int margin;

    public boolean hasCollision(int xMoved, int yMoved, Board board) {
        initiateVariables(board);

        for (int y = 0; y < polyHeight; y++) {
            for (int x = 0; x < polyWidth; x++) {
                if (poly.getBlock(x, y) != SquareType.EMPTY) {
                    if (board.getBlock(x + polyX + xMoved + margin, y + polyY + yMoved + margin) != SquareType.EMPTY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected void initiateVariables(Board board) {
        poly = board.getFalling();
        polyY = poly.getY();
        polyX = poly.getX();
        polyHeight = poly.getHeight();
        polyWidth = poly.getWidth();
        margin = board.getMargin();
    }
}
