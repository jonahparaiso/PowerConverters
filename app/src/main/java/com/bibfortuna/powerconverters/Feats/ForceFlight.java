package com.bibfortuna.powerconverters.Feats;

import com.bibfortuna.powerconverters.CheckTable;

public class ForceFlight extends Feat {
    public ForceFlight() {
        super("FORCE FLIGHT", "You can wrap yourself in the Force and move yourself toward a specific destination.\n" +
                "   Prerequisite: Alter, Force-Sensitive, Move Object 6 ranks, Force level 7th\n" +
                "   Benefit: You gain the ability to control the Force to a greater degree and can use it\n" +
                "       to move yourself from one spot to another. Doing this requires a successful Move Object\n" +
                "       check and vitality cost of 4. You can take 10 but you can’t take 20 on this check. The\n" +
                "       distance you travel either horizontally or vertically as a move action is determined by\n" +
                "       the check result.");
        prereqs.put("ALTER", 1);
        prereqs.put("FORCE SENSITIVE", 1);
        //Move Object is forceSkills[17], which is 18 units above the first skill tree or 38, so index is the sum of the two
        prereqs.put("56", 6);
        prereqs.put("FLV", 7);
        setCanTake10(true);
        setIsActivatedAbility(true);

        this.table = new CheckTable(20, 25,
                "Distance: 10 meters", "Distance: 15 meters", "Distance: 20 meters",
                4, 4, 4);
    }
}
