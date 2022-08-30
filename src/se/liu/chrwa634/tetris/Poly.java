package se.liu.chrwa634.tetris;

/**
 * This class represents a Poly which is a cluster of SquareTypes that makes a tetris block. It is represented
 * As an array of SquareTypes which is given at object initialization. The class has a function to ratate this object
 * either to the left or to the right.
 */
public class Poly
{
    private SquareType[][] polyomino;
    private int x;
    private int y;

    public Poly(SquareType[][] polyomino) {
        this.polyomino = polyomino;
    }

    /**
     * This method returns the height of the polymino.
     * @return The height of the polymino.
     */
    public int getHeight() {
        return polyomino.length;
    }

    /**
     * This method returns the width of the polymino.
     * @return the width of the polymino.
     */
    public int getWidth() {
        return polyomino[0].length;
    }

    /**
     * This method returns the x of the polyomino.
     * @return the x of the polyomino.
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns the y of the polyomino.
     * @return The y of the polyomino.
     */
    public int getY() {
        return y;
    }
    
    /**
     * This method get the block at a specific position on the polymino the upper left corner is (0,0);
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return The block at the given position.
     */
    public SquareType getBlock(int x, int y) {
        return polyomino[y][x];
    }

    /**
     * This method sets the to a new object.
     * @param polymino
     */
    public void setPolyomino(SquareType[][] polyomino) {
        this.polyomino = polyomino;
    }

    /**
     * This method sets the x of the polyomino.
     * @param x The value it will be set to.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This method sets the y of the polyomino.
     * @param y The value it will be set to.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This method rotates the poly object.
     * @param direction The direction in which the object will be rotated.
     */
    public void rotate(Direction direction) {
        if (direction == Direction.RIGHT) {
            setPolyomino(rotateRight());
        }
        else {
            setPolyomino(rotateLeft());
        }
    }

    /**
     * This method returns the squares of the poly if it were to be rotated to the right.
     * @return An array of squyare depicting the polymino.
     */
    private SquareType[][] rotateRight() {
        SquareType[][] newSquares = new SquareType[getHeight()][getWidth()];
        int width = getWidth();
        int height = getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                newSquares[x][height - 1 - y] = getBlock(x, y);
            }
        }
        return newSquares;
    }

    /**
     * This method returns the squares of the poly if it were to be rotated to the left.
     * @return An array of square depicting the polymino.
     */
    private SquareType[][] rotateLeft() {
        SquareType[][] newSquares = new SquareType[getHeight()][getWidth()];
        int width = getWidth();
        int height = getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newSquares[height - 1 - x][y] = getBlock(x, y);
            }
        }
        return newSquares;
    }
}
