package com.bibfortuna.powerconverters;

import org.junit.Test;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DiceTest {
    int sampleSize = 100;

    /** Why is testing for variance important?
     *
     *   Suppose you roll ten 10 sided die. The expected roll for a single 10 sided die roll is
     *   5.5, which is 11/5. This is true because 1, 2, 3, 4, 5, 6, 7, 8, 9, and 10 all have
     *   equal chances of being rolled. Specifically, for an N sided die, the expected roll
     *   is (N+1)/2. Therefore, if you roll X N sided die, then the expected sum is X*(N+1)/2.
     *
     *   Here we want to find how close ALL rolls are to their expected values. Suppose we only
     *   make two such rolls, 4 d4 and 5 d5. If 4 d4 are rolled, expect 4*(5/2), or 10. However,
     *   it rolled horribly, rolling 1, 1, 2, and 2, resulting in 6. This is a factor of .6
     *   of the expected result. For the next roll, 5 d5 are rolled, and it is wonderfully
     *   high with 5, 5, 4, 3, 5, summing 22 (well over the expected 15). This result is a
     *   factor of 1.46.
     *
     *   Overall, the total factor average is .6 + 1.46 / 2, or 1.03. The average should always
     *   be around .5.
     * */

    @Test
    public void playRoll() {
        int d2, d4, d6, d8, d10, d12, d20, d100;
        float variance;
        Dice die = new Dice();
        d2 = die.roll(2);
        d4 = die.roll(4);
        d6 = die.roll(6);
        d8 = die.roll(8);
        d10= die.roll(10);
        d12= die.roll(12);
        d20= die.roll(20);
        d100=die.roll(100);
        variance = (float) (d2 + d4 + d6 + d8 + d10 + d12 + d20 + d100)/85;
        System.out.printf("Rolls:%nd2: %d%nd4: %d%nd6: %d%nd8: %d%nd10: %d%n", d2, d4, d6, d8, d10);
        System.out.printf("d12: %d%nd20: %d%nd100: %d%nVariance: %f%n", d12, d20, d100, variance);
    }

    @Test
    public void testRoll() {
        int lowRolls = 0;
        int highRolls = 0;
        double variance = 0;//Specifies how close the roll is to the expected roll (n/2+.5)
        float expectedRoll;
        int totalRolls = sampleSize;
        Dice die = new Dice();
        int result;
        System.out.printf("Testing roll %d times%n", totalRolls);

        //plus one because roll() doesn't work on d(0) and this algorithm starts at array[i] with d(i)
        for (int i = 2; i < totalRolls+1; i++) {
            result = die.roll(i);
            expectedRoll = (i+1)/2;
            variance += (result/expectedRoll);
            if(result == 1) {
                lowRolls++;
                //System.out.printf("Min rolled on roll(%d)%n", i);
            }
            if(result == i) {
                highRolls++;
                //System.out.printf("Max rolled on roll(%d)%n", i);
            }
            assert(result > 0 && result <= i);
        }
        variance /= totalRolls;
        assert(variance < 1.3 && variance > .7);
        System.out.printf("%nFinished running testRoll%nTotal minimum rolls: %d%n", lowRolls);
        System.out.printf("Total maximum rolls: %d%nVariance of all rolls (should be ~1): %f%n%n", highRolls, variance);
    }

    @Test
    public void testRollX() {
        int lowRolls = 0;
        int highRolls = 0;
        double variance = 0;
        float expectedRoll;
        int totalRolls = sampleSize;
        int result;
        Dice die = new Dice();
        System.out.printf("Testing rollX %d times%n", totalRolls);

        for (int i = 2; i < totalRolls+1; i++) {
            result = die.rollX(i,i);
            expectedRoll = i*(i+1)/2;
            variance += (result/expectedRoll);

            if(result == i) {
                lowRolls++;
                //System.out.printf("Min rolled on rollX(%d,%d)%n", i, i);
            }
            if(result == i*i) {
                highRolls++;
                //System.out.printf("Max rolled on rollX(%d,%d)%n", i, i);
            }
            assert(result >= i && result <= i*i);
        }
        variance /= totalRolls;
        assert(variance < 1.2 && variance > .8);
        System.out.printf("%nFinished running testRollX%nTotal minimum rolls: %d%n", lowRolls);
        System.out.printf("Total maximum rolls: %d%nVariance of all rolls (should be ~1): %f%n%n", highRolls, variance);
    }

    @Test
    public void testBestOfRange() {
        int lowRolls = 0;
        int highRolls = 0;
        double variance = 0;
        float expectedRoll;
        int totalRolls = sampleSize;
        Dice die = new Dice();
        int diceKept, diceRolled, diceType;
        int result;
        System.out.printf("Testing bestOfRange %d times%n", totalRolls);

        for (int i = 2; i < totalRolls+1; i++) {
            diceType = i;
            diceRolled = i;
            diceKept = i/2;
            result = die.bestOf(diceKept, diceRolled, diceType);
            assert(result >= diceKept && result <= diceKept*diceType);

            expectedRoll = (((float) diceKept * ((diceType + 1))) / 2);
            variance += (result/expectedRoll);
            if(result == diceKept) {
                lowRolls++;
                //System.out.printf("Min rolled on best %d d%d of %d%n", diceKept, diceType, diceRolled);
            }
            if(result == diceKept*diceType) {
                highRolls++;
                //System.out.printf("Max rolled on best %d d%d of %d%n", diceKept, diceType, diceRolled);
            }
        }

        variance /= totalRolls;
        System.out.printf("%nFinished running testBestOfRange%nTotal minimum rolls: %d%n", lowRolls);
        System.out.printf("Total maximum rolls: %d%nVariance of all rolls (should be >1): %f%n%n", highRolls, variance);
    }
}
