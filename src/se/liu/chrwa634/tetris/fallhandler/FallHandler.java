package se.liu.chrwa634.tetris.fallhandler;

import se.liu.chrwa634.tetris.Board;

public interface FallHandler
{
    public boolean hasCollision(int xMoved, int yMoved, Board board);

    public String getName();
}
