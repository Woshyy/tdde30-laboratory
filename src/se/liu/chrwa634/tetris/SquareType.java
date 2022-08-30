package se.liu.chrwa634.tetris;

import java.util.Random;

/**
 * This is class implements the different types of square blocks.
 * The diffrent types of Squares are EMPTY, I, O, T, S, Z, J, L.
 */
public enum SquareType
{
    EMPTY, I, O, T, S, Z, J, L, OUTSIDE;

    public static void main(String[] args) {
        Random rnd = new Random(); //creates a random generator.
	//chose a random Squaretype 25 diffrent times.
	for (int i = 0; i < 26; i++) {
	    System.out.println(SquareType.values()[rnd.nextInt(SquareType.values().length)]); //chose a random number for the index
	}
    }
}
