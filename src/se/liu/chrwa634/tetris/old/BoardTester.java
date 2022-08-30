package se.liu.chrwa634.tetris.old;

import se.liu.chrwa634.tetris.Board;
import se.liu.chrwa634.tetris.highscore.HighscoreList;

public class BoardTester
{
    public static void main(String[] args) {
	HighscoreList highscoreList = new HighscoreList();
	Board board = new Board(20, 20);
	/*
	for (int i = 0; i < 11; i++) {
	    board.randomizeBoard();
	    System.out.println(BoardToTextConverter.convertToText(board));
	} */
    }
}
