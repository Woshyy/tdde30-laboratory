package se.liu.chrwa634.tetris;

import se.liu.chrwa634.tetris.fallhandler.DefaultFallHandler;
import se.liu.chrwa634.tetris.fallhandler.FallHandler;
import se.liu.chrwa634.tetris.fallhandler.Fallthrough;
import se.liu.chrwa634.tetris.fallhandler.Heavy;
import se.liu.chrwa634.tetris.highscore.Highscore;
import se.liu.chrwa634.tetris.highscore.HighscoreList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The class that represent the playing field. It has information about which squares are currently at a specific coordinate.
 * The class has also functionality to modifty the squares on the board.
 */
public class Board
{
    private static final int MARGIN = 2;
    private static final int DOUBLE_MARGIN = 2 * MARGIN;
    private static final int FALLTHROUGH_RATE = 10;
    private static final int HEAVY_BLOCK_BREAK = 2;
    private static final Map<Integer, Integer> POINTMAP = Map.of(0, 0, 1, 100, 2, 300, 3, 500, 4, 800);
    private static final Random RND = new Random();

    private SquareType[][] squares = null;
    private Poly falling = null;
    private TetrominoMaker polyMaker = new TetrominoMaker();
    private List<BoardListener> boardListeners = new ArrayList<>();
    private HighscoreList highscoreList;
    private FallHandler fallHandler = new DefaultFallHandler();

    private boolean gameOver = false;
    private boolean pause = false;

    private int score = 0;
    private int polyCounter = 0;

    public Board(int height, int width) {
	this.highscoreList = HighscoreList.loadHighscoreList();
	highscoreList.drawString();

	resetBoard(width, height);

	/**
	 // Test break, Heavy and fallThough
	for (int y = 10; y < height + MARGIN; y++) {
	    for (int x = MARGIN; x < width + MARGIN; x++) {
		squares[y][x] = SquareType.O;
	    }
	}

	for (int x = MARGIN; x < width + MARGIN; x++) {
	    squares[12][x] = SquareType.EMPTY;
	}

	for (int x = MARGIN; x < width + MARGIN; x++) {
	    squares[13][x] = SquareType.EMPTY;
	}

	for (int x = MARGIN; x < width + MARGIN; x++) {
	    squares[16][x] = SquareType.EMPTY;
	}

	for (int x = MARGIN; x < width + MARGIN; x++) {
	    squares[18][x] = SquareType.EMPTY;
	 }
	 */

	//Fallthrough activation test.1
	//setPolyCounter(8);
    }

    /**
     * Returns the width of the board as an integer.
     * @return the width.
     */
    public int getWidth() {
	return squares[0].length - DOUBLE_MARGIN;
    }

    /**
     * Returns the height of the board as an integer.
     * @return the height.
     */
    public int getHeight() {
	return squares.length - DOUBLE_MARGIN;
    }

    /**
     * Returns the SquareType of the specific coordinate on the board.
     * @param x the x coordinate on the board.
     * @param y the y coordinate on the board.
     * @return the SquareType of the given block coordinate.
     */
    public SquareType getBlock(int x, int y) {
	return squares[y][x];
    }

    /**
     * Returns the falling object.
     * @return The falling object as a poly object.
     */
    public Poly getFalling() {
	return falling;
    }

    /**
     * Returns the fallhanlder object.
     * @return Fallhandler object.
     */
    public FallHandler getFallHandler() {
        return fallHandler;
    }

    /**
     * This method returns the current score.
     * @return The score.
     */
    public int getScore() {
	return score;
    }

    /**
     * This method returns the value of gameOver.
     * @return The value of gameOver.
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * This method retusn the value of pause.
     * @return The value of pause.
     */
    public boolean getPause() {
        return pause;
    }

    /**
     * This method retrieves the margin of outside blocks.
     * @return The margin as an int.
     */
    public int getMargin() {
        return MARGIN;
    }

    public int getPoint(int rows) {
	int highestRowBreak = POINTMAP.values().size();
        if (rows > highestRowBreak) {
            int point = POINTMAP.get(highestRowBreak - 1);
            for (int i = 0; i < rows - highestRowBreak; i++ ) {
		point *= 2;
	    }
            return point;
	}
        return POINTMAP.get(rows);
    }

    public int getPolyCounter() {
        return polyCounter;
    }

    /**
     * Get a new falling and draw it on the board.
     */
    private void setFalling() {
	falling = polyMaker.getPoly(RND.nextInt(polyMaker.getNumberOfTypes()));
	falling.setX(Math.floorDiv(getWidth(), 2));
	falling.setY(0);
	if (hasCollision(0, 0)) {
	    gameOver = true;
	} else {
	    notifyListeners();
	}
    }

    /**
     * This method sets the gameOver flag to a specific value.
     * @param value
     */
    private void setGameOver(boolean value){
        gameOver = value;
    }

    /**
     * This method sets the pause flag to the given value.
     * @param value The value that the pause flag would be set to.
     */
    public void setPause(boolean value) {
        pause = value;
    }

    /**
     * This method sets the blocks on the board to a given type of block at the given coordinate.
     * the coordinate (x = 0, y = 0) is on then left upper conner.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param block The block that will be placed at the coordinate.
     */
    public void setBlock(int x, int y, SquareType block) {
        squares[y][x] = block;
    }

    public void setFallHandler(FallHandler fallHandler) {
        this.fallHandler = fallHandler;
    }

    public void resetFallHandler() {
        setFallHandler(new DefaultFallHandler());
    }

    public void setPolyCounter(int value) {
        polyCounter = value;
    }

    public void resetPolyCounter() {
        setPolyCounter(0);
    }

    public void increasePolyCounter() {
        setPolyCounter(getPolyCounter() + 1);
    }

    /**
     * This method increase the score as the given amount.
     * @param amount The amount that will be added.
     */
    private void addScore(int amount) {
	score += amount;
    }

    /**
     * Adds a boardlistener to the list of boardlisteners.
     * @param bl a board listener.
     */
    public void addBoardListener(BoardListener bl) {
	boardListeners.add(bl);
    }


    /**
     * This method resets the board by setting the entire board to SquareType.EMPTY or SquareType.OUTSIDE.
     * It also sets the flag gameOver to false and pause to false.
     */
    public void resetBoard(int width, int height) {
        //Create a new array that will be the new board.
	squares = new SquareType[height + DOUBLE_MARGIN][width + DOUBLE_MARGIN];

	//Add outside blocks and empty blocks to the board.
	for (int y = 0; y < height + DOUBLE_MARGIN; y++) {
	    for (int x = 0; x < width + DOUBLE_MARGIN; x++) {
		if (y < MARGIN || y >= height + MARGIN || x < MARGIN || x >= width + MARGIN) {
		    setBlock(x, y, SquareType.OUTSIDE);
		} else {
		    setBlock(x, y, SquareType.EMPTY);
		}
	    }
	}

	setGameOver(false);
	setPause(false);
	resetPolyCounter();
    }

    /**
     * A function that randomize every block in the board.
     */
    public void randomizeBoard() {
        //Goes through every single block on the board and randomize it.
	for (int y = 0; y < getHeight(); y++) {
	    for (int x = 0; x < getWidth(); x++) {
		setBlock(x, y, SquareType.values()[RND.nextInt(SquareType.values().length)]);
	    }
	}

	notifyListeners();
    }


    /**
     * Notify all the listeners that an update has been done to the board.
     */
    private void notifyListeners() {
	for (BoardListener bl : boardListeners) {
	    bl.boardChanged();
	}
    }

    /**
     * Updates the board to the next state. If there arent any falling than spawn one.
     * If the falling has fallen then update the board. If the game is over then stop any update.
     * If the board cannot place a block then the game is over.
     */
    public void tick() {
        if (!getPause()) {
            if (getGameOver()) {
		setPause(true);
		openGameOverScreen();
	    } else {
		if (!isFalling()) {
		    setFalling();
		    increasePolyCounter();
		    if (getPolyCounter() % FALLTHROUGH_RATE == 0) {
		        setFallHandler(new Fallthrough());
		    }
		} else {
		    if (hasCollision(0, 1)) {
			drawFalling();
			falling = null;
			resetFallHandler();
			updateBoard();
		    } else {
			falling.setY(falling.getY() + 1);
		    }
		}
	    }
	}
	notifyListeners();
    }

    /**
     * Update the board by finding fullrows. If a fullrow is found remove all the blocks and move everything over it by one.
     * The fuctions starts at the top and finish at the bottom of the board.
     */
    private void updateBoard() {
        int numberOfFullRows = 0;
        //check if any row does not consist of an empty squaretype...
	for (int y = MARGIN; y < getHeight() + MARGIN; y++) {
	    boolean fullRow = true;
	    for (int x = MARGIN; x < getWidth() + MARGIN; x++) {
	        SquareType block = getBlock(x, y);
		if (block.equals(SquareType.EMPTY)) {
		    fullRow = false;
		    break;
		}
	    }
	    //if the row were full then we need to remove it. And increase the number of full rows.
	    if (fullRow) {
	        clearRow(y);
	        numberOfFullRows += 1;
	    }
	}

	//If more than 2 rows are filled then the user get a heavy powerup.
	if (numberOfFullRows >= HEAVY_BLOCK_BREAK) {
	    setFallHandler(new Heavy());
	}

	addScore(getPoint(numberOfFullRows));
    }

    /**
     * A method that removes the given row and move everything over it by one.
     * @param rowIndex The index of the row which is going to be removed.
     */
    private void clearRow(int rowIndex) {
        //removing the row from the board
	for (int x = MARGIN; x < MARGIN + getWidth(); x++) {
	    setBlock(x, rowIndex, SquareType.EMPTY);
	}

	//move everything above it by one from the bottom
	for (int y = rowIndex; y >= MARGIN; y--) {
	    for (int x = MARGIN; x < getWidth() + MARGIN; x++) {
		if (!(getBlock(x, y) == SquareType.OUTSIDE || getBlock(x, y) == SquareType.EMPTY)) {
		    setBlock(x,y + 1, getBlock(x, y));
		    setBlock(x, y, SquareType.EMPTY);
		}
	    }
	}
    }



    /**
     * Move the falling object either right or left. The fuctions check if that move is possible.
     * @param direction The direction the falling polymino would move.
     */
    public void move(Direction direction) {
        //Check if the game is not over, not in pause, and there is a falling polyomino.
	if (!gameOver && isFalling() && !getPause()) {
	    int move = 0;
	    //check direction and if it would collide with any block already on the board.
	    if (direction == Direction.RIGHT && !hasCollision(1, 0)) {
		move = 1;
	    } else if (direction == Direction.LEFT && !hasCollision(-1, 0)) {
		move = -1;
	    }
	    falling.setX(falling.getX() + move);
	    notifyListeners();
	}
    }

    /**
     * Rotate the poly either to the right or to the left if it is possible.
     * @param dir The direction in which the poly object will be rotated.
     */
    public void rotate(Direction dir) {
        //Check if there is a falling poly, the game is not pause and the game is not over.
        if (isFalling() && !gameOver && !getPause()) {
	    //check if it is right or left
	    falling.rotate(dir);
	    //check if it is going to colide
	    if (hasCollision(0, 0)) {
		if (dir.equals(Direction.RIGHT)) {
		    falling.rotate(Direction.LEFT);
		}
		else {
		    falling.rotate(Direction.RIGHT);
		}
	    }
	}
        notifyListeners();
    }

    /**
     * Draw the falling poly on the board.
     */
    private void drawFalling() {
        int fallingHeight = falling.getHeight();
        int fallingWidth = falling.getWidth();
        int fallingX = falling.getX();
        int fallingY = falling.getY();

	for (int y = 0; y < fallingHeight; y++) {
	    for (int x = 0; x < fallingWidth; x++) {
		SquareType block = falling.getBlock(x, y);
		SquareType blockOnBoard = getBlock(fallingX + x + MARGIN, fallingY + y + MARGIN);

		//If the block are not the same already and that the the falling block is not EMPTY then...
		if (blockOnBoard == SquareType.EMPTY && block != SquareType.EMPTY) {
		    //We need to change it.
		    setBlock(fallingX + x + MARGIN, fallingY + y + MARGIN, block);
		}
	    }
	}
    }

    /**
     * Check if a given movement would collide with anyother block that is already on the board.
     * @param xMoved The movement in x coordinates.
     * @param yMoved The movement in y coordinates.
     * @return Returns true if it would collide otherwise false.
     */
    private boolean hasCollision(int xMoved, int yMoved) {
	return fallHandler.hasCollision(xMoved, yMoved, this);
    }

    /**
     * Check if there is any falling blocks.
     * @return True if there is falling blocks otherwise false.
     */
    private boolean isFalling() {
        return falling != null;
    }

    /**
     * This method opens up the gameOver screen.
     */
    public void openGameOverScreen() {
        GameOverProcedure gameOverProcedure = new GameOverProcedure(this);
    }

    /**
     * This method adds a highscore to the highscore leaderboard.
     * @param highscore The highscore object that will be added.
     * @throws IOException when reading or writing to the json file fails.
     */
    public void putHighscore(Highscore highscore) throws IOException {
        highscoreList.putHighscore(highscore);
        highscoreList.saveHighscoreList();
	highscoreList.drawString();
    }
}
