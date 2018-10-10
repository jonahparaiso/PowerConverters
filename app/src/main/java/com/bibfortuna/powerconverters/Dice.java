package com.bibfortuna.powerconverters;

import java.util.Random;
import java.util.Arrays;

public class Dice {
    private int out;
    private Random rand;

    /**Rolls just one die of type d
     *
     * @param d The type of die being rolled.
     * @return The output
     */
    protected int roll(int d) {
        rand = new Random();

        /**Checking to see if it is a regular die. Still works on irregular sided dice.
        if (d != 2 && d != 4 && d != 6 && d != 8 && d != 10 && d != 12 && d != 20 && d != 100)
            System.out.printf("What an odd die you have, never seen a d%d before...%n", d);*/

        //Actual die roll
        out = rand.nextInt(d) + 1;
        return out;
    }

    /**Rolls x dice of type d and returns the sum
     *
     * @param x How many dice are being rolled.
     * @param d The type of dice being rolled, ie a "d20" or a "d6".
     * @return The total sum.
     */
    protected int rollX(int x, int d) {
        //System.out.printf("Rolling %d d%d's%n", x, d);
        int temp;
        out = 0;
        rand = new Random();

        while (x > 0) {
            temp = rand.nextInt(d) + 1;
            //System.out.printf("Rolled a %d%n", temp);
            out += temp;
            x--;
        }
        //System.out.printf("rollX(x,%d) returned: %d%n%n", d, out);
        return out;
    }

    /**TODO can be improved in time complexity and space, I think.
     * Primarily used for attribute generation, which uses the best 3 of 4 d6 rolls. Might be useful.
     *
     * @param diceKept The number of dice that are included in the roll; the amount of dice we care about.
     * @param diceRolled The total number of dice that are rolled, not all results are included in the end.
     * @param diceType The type of dice being rolled, ie a "d20" or a "d6".
     */
    protected int bestOf(int diceKept, int diceRolled, int diceType) {
        if (diceKept >= diceRolled || diceKept < 1 || diceRolled < 1 || diceType < 0) {
            System.out.println("Invalid argument for bestOf, try 'best 3 of 5' or best '8 of 10'");
            return 0;
        }

        out = 0;
        rand = new Random();
        int[] rolls = new int[diceRolled];

        for (int i = 0; i < diceRolled; i++) {
            rolls[i] = rand.nextInt(diceType) + 1;
            //System.out.printf("Rolled a %d. ", rolls[i]);
        }

        //Sort the array into ascending order, then get greatest numbers from the back
        Arrays.sort(rolls);
        for (int i = 1; i <= diceKept; i++) {
            //System.out.printf("%nOut = %d", out);
            out += rolls[diceRolled - i];
        }

        //System.out.printf("BestOf is returning %d%n", out);
        return out;
    }


}
