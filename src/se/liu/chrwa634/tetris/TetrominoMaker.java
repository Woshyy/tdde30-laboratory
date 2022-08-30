package se.liu.chrwa634.tetris;

/**
 * This is a class that creates the different types of tetromino. The main method getPoly returns
 * a poly object of a specific type.
 */
public class TetrominoMaker
{
    private final static int NUMBER_OF_TYPES = 7;

    public TetrominoMaker() {
    }

    /**
     * This method returns the number of types.
     * @return The number of types as an integer.
     */
    public int getNumberOfTypes() {
	return NUMBER_OF_TYPES;
    }

    /**
     * This method returns a Poly object of a specifc type.
     * @param n The type that will be given.
     * @return The poly object.
     */
    public Poly getPoly(int n) {
	if (n > NUMBER_OF_TYPES - 1 || n < 0) {
	    throw new IllegalArgumentException("Invalid index: " + n);
	} else {

	    switch (n) {
		case 0:
		    return new Poly(type0());
		case 1:
		    return new Poly(type1());
		case 2:
		    return new Poly(type2());
		case 3:
		    return new Poly(type3());
		case 4:
		    return new Poly(type4());
		case 5:
		    return new Poly(type5());
		case 6:
		    return new Poly(type6());
		default:
		    throw new IllegalArgumentException("Something went wrong");

	    }
	}
    }

    private SquareType[][] type0() {
        SquareType[][] result = new SquareType[4][4];

	for (int y = 0; y < 4; y++) {
	    for (int x = 0; x < 4; x++) {
		if(y == 1) {
		    result[y][x] = SquareType.I;
		}
		else {
		    result[y][x] = SquareType.EMPTY;
		}
	    }
	}
	return result;
    }

    private SquareType[][] type1() {
        SquareType[][] result = new SquareType[3][3];

	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 3; x++) {
	        if (y == 1) {
	            result[y][x] = SquareType.J;
		}
	        else {
	            result[y][x] = SquareType.EMPTY;
		}

	    }
	}
        result[0][0] = SquareType.J;
	return result;
    }

    private SquareType[][] type2() {
        SquareType[][] result = new SquareType[3][3];

	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 3; x++) {
		if (y == 1) {
		    result[y][x] = SquareType.L;
		}
		else {
		    result[y][x] = SquareType.EMPTY;
		}
	    }
	}
	result[0][2] = SquareType.L;
	return result;
    }

    private SquareType[][] type3() {
        SquareType[][] result = new SquareType[2][2];

	for (int y = 0; y < 2; y++) {
	    for (int x = 0; x < 2; x++) {
	        result[y][x] = SquareType.O;
	    }
	}
	return result;
    }

    private SquareType[][] type4() {
        SquareType[][] result = new SquareType[3][3];

	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 3; x++) {
	        if (y == 2) {
	            result[y][x] = SquareType.EMPTY;
		}
	        else {
	            result[y][x] = SquareType.S;
		}
	    }
	}
	result[0][0] = SquareType.EMPTY;
	result[1][2] = SquareType.EMPTY;
	return result;
    }

    private SquareType[][] type5() {
        SquareType[][] result = new SquareType[3][3];

	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 3; x++) {
		if (y == 2) {
		    result[y][x] = SquareType.EMPTY;
		}
		else {
		    result[y][x] = SquareType.T;
		}
	    }
	}

	result[0][0] = SquareType.EMPTY;
	result[0][2] = SquareType.EMPTY;

	return result;
    }

    private SquareType[][] type6() {
        SquareType[][] result = new SquareType[3][3];

	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 3; x++) {
		if (y == 2) {
		    result[y][x] = SquareType.EMPTY;
		}
		else {
		    result[y][x] = SquareType.Z;
		}
	    }
	}
	result[1][0] = SquareType.EMPTY;
	result[0][2] = SquareType.EMPTY;

	return result;
    }
}
