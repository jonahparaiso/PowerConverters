package com.bibfortuna.powerconverters;

public class CheckTable {
    private int[] dc;
    private String[] result;
    private int[] vpCost;

    public CheckTable(int[] dcArr, String[] resultArr, int[] vpArr) {
        dc = dcArr;
        result = resultArr;
        vpCost = vpArr;
    }

    /**
     * Creates a table of DCs versus results. For an example of a chart, see below:
     *
     * Check chart = new Check(15, 20, 25, 30,
     *                              "Fail to climb tree at all",// <15
     *                              "Get close to location, but slip and fall. Take 1d6 + 1 dmg.",// 15<roll>20
     *                              "Successfully climb tree and have an okay footing, but it takes you 1d4 minutes to do so.",// 20<roll>25
     *                              "Climb the tree with ease, quickly making it up in less than one minute.",// 25<roll>30
     *                              "Speed up the tree with amazing swiftness in one round or 6 seconds.";//30<roll
     * @param dc0 If the roll is below this number, return r0
     * @param dc1 dc0 <= roll < dc1 represents the space in between {dc0 and dc1), return r1
     * @param dc2 dc1 <= roll < dc2 represents the space in between {dc1 and dc2), return r2
     * @param dc3 dc2 <= roll < dc3 represents the space in between {dc2 and dc3), return r3
     * @param r0 See above
     * @param r1 See above
     * @param r2 See above
     * @param r3 See above
     * @param r4 Represents the highest possible result.
     */
    public CheckTable(int dc0, int dc1, int dc2, int dc3, String r0, String r1, String r2, String r3, String r4, int cost0, int cost1, int cost2, int cost3, int cost4) {
        dc = new int[]{dc0, dc1, dc2, dc3, 999};
        result = new String[]{r0, r1, r2, r3, r4};
        vpCost = new int[]{cost0, cost1, cost2, cost3, cost4};
    }

    public CheckTable(int dc0, int dc1, int dc2, String r0, String r1, String r2, String r3, int cost0, int cost1, int cost2, int cost3) {
        dc = new int[]{dc0, dc1, dc2, 999};
        result = new String[]{r0, r1, r2, r3};
        vpCost = new int[]{cost0, cost1, cost2, cost3};
    }

    public CheckTable(int dc0, int dc1, String r0, String r1, String r2, int cost0, int cost1, int cost2) {
        dc = new int[]{dc0, dc1, 999};
        result = new String[]{r0, r1, r2};
        vpCost = new int[]{cost0, cost1, cost2};
    }

    public CheckTable(int dc0, String r0, String r1, int cost0, int cost1) {
        dc = new int[]{dc0, 999};
        result = new String[]{r0, r1};
        vpCost = new int[]{cost0, cost1};
    }

    public CheckTable(int dc0, int dc1, int dc2, int dc3, int dc4, int dc5, int dc6,
                      String r0, String r1, String r2, String r3, String r4, String r5, String r6, String r7,
                      int cost0, int cost1, int cost2, int cost3, int cost4, int cost5, int cost6, int cost7) {
        dc = new int[]{dc0, dc1, dc2, dc3, dc4, dc5, dc6, 999};
        result = new String[]{r0, r1, r2, r3, r4, r5, r6, r7};
        vpCost = new int[]{cost0, cost1, cost2, cost3, cost4, cost5, cost6, cost7};
    }

    /**
     * If a hero is to use a skill or feat that requires a dice check, then they would roll a d20 and then
     * add appropriate modifiers to the roll. For example, Chewy wants to climb a tree, and the DC's for the
     * tree are 15, 20, 25, and 30 (set by the DM). Chewy rolls a 15, then adds 4 for his STR of 18, then adds
     * another 6 for his ranks, and finally another 2 from his Athletic feat. His check is 15+4+6+2 for a
     * roll of 27. This is greater than DC 25 but not equal to or greater than 30, so Chewy would do whatever
     * is listed for the result of DC 25.
     *
     * How that translates here is this:
     *      //Climb has index 4 in the skill array
     *      int skillcheck = Chewy.skills[4].skillCheck(die.roll(20));
     *      Check chart = new Check(15, 20, 25, 30,
     *                              "Fail to climb tree at all",
     *                              "Get close to location, but slip and fall. Take 1d6 + 1 dmg.",
     *                              "Successfully climb tree and have an okay footing, but it takes you 1d4 minutes to do so.",
     *                              "Climb the tree with ease, quickly making it up in less than one minute.");
     *      String output = chart.makeCheck(skillCheck);
     *
     *      System.out.println(output);
     *
     * Above code should print out "Successfully climb tree and have an okay footing...".
     *
     * @param roll Total skill check. Represents the sum of a players skill or feat d20 roll and appropriate modifiers.
     * @return The resulting effects of the roll, which could set a DC check for an opponent or otherwise
     *  have some effect on something.
     */
    public String makeCheck(int roll) {
        int compareAbove, compareBelow;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < dc.length; i++) {
            compareAbove = dc[i];
            if (i == 0)
                if (roll < compareAbove) {
                    out.append(result[i]);
                    out.append("%nvitality cost: ");
                    out.append(vpCost[i]);
                    return out.toString();
                }
            else {
                compareBelow = dc[i - 1];
                if (roll >= compareBelow && roll < compareAbove) {
                    out.append(result[i]);
                    out.append("%nvitality cost: ");
                    out.append(vpCost[i]);
                    return out.toString();
                    }
                }
        }
        //If this is reached, then the roll is higher than the highest value in dc[]. That last element should always be 999
        return "Error";
    }
}
