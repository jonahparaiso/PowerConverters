package com.bibfortuna.powerconverters;

public class DiceTester {
    Dice die;

    public DiceTester() { die = new Dice(); }

    public static void main(String[] args) {
        DiceTester dieTester = new DiceTester();
        int a, b, c, d, e, f, g, h, i, j, k, mean;
        a = dieTester.die.roll(20);
        b = dieTester.die.roll(20);
        c = dieTester.die.roll(20);
        d = dieTester.die.roll(20);
        e = dieTester.die.roll(20);
        f = dieTester.die.roll(20);
        g = dieTester.die.roll(20);
        h = dieTester.die.roll(20);
        i = dieTester.die.roll(20);
        j = dieTester.die.roll(20);
        k = dieTester.die.roll(20);
        mean = (a + b + c + d + e + f + g + h + i + j + k)/10;
        System.out.printf("Testing roll(20) ten times. Results:%na : %d%nb : %d%nc : %d%nd : %d%ne :" +
                "%d%nf : %d%n g : %d%n h : %d%n i : %d%n j : %d%n k : %d%nmean : %d%n%n",
                a, b, c, d, e, f, g, h, i, j, k, mean);

        a = dieTester.die.rollX(20, 10);
        b = dieTester.die.rollX(20, 10);
        c = dieTester.die.rollX(20, 10);
        d = dieTester.die.rollX(20, 10);
        e = dieTester.die.rollX(20, 10);
        f = dieTester.die.rollX(20, 10);
        g = dieTester.die.rollX(20, 10);
        h = dieTester.die.rollX(20, 10);
        i = dieTester.die.rollX(20, 10);
        j = dieTester.die.rollX(20, 10);
        k = dieTester.die.rollX(20, 10);
        mean = (a + b + c + d + e + f + g + h + i + j + k)/10;
        System.out.printf("Testing rollX(20,10) ten times. Results:%na : %d%nb : %d%nc : %d%nd : %d%n" +
                        "e : %d%nf : %d%n g : %d%n h : %d%n i : %d%n j : %d%n k : %d%nmean : %d%n%n",
                a, b, c, d, e, f, g, h, i, j, k, mean);

        a = dieTester.die.bestOf(3, 4, 6);
        b = dieTester.die.bestOf(3, 4, 6);
        c = dieTester.die.bestOf(3, 4, 6);
        d = dieTester.die.bestOf(3, 4, 6);
        e = dieTester.die.bestOf(3, 4, 6);
        f = dieTester.die.bestOf(3, 4, 6);
        g = dieTester.die.bestOf(3, 4, 6);
        h = dieTester.die.bestOf(3, 4, 6);
        i = dieTester.die.bestOf(3, 4, 6);
        j = dieTester.die.bestOf(3, 4, 6);
        k = dieTester.die.bestOf(3, 4, 6);
        mean = (a + b + c + d + e + f + g + h + i + j + k)/10;
        System.out.printf("Testing bestOf(3,4,6) ten times. Results:%na : %d%nb : %d%nc : %d%nd : %d%n" +
                        "e : %d%nf : %d%n g : %d%n h : %d%n i : %d%n j : %d%n k : %d%nmean : %d%n%n",
                a, b, c, d, e, f, g, h, i, j, k, mean);
    }
}
