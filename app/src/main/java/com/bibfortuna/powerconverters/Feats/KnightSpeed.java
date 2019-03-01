package com.bibfortuna.powerconverters.Feats;

public class KnightSpeed extends Feat {
    public KnightSpeed() {
        super("KNIGHT SPEED", "The Force allows you to move extremely fast for a brief period of time.\n" +
                "   Prerequisite: Force-Sensitive, Control, Burst of Speed, Jedi level 7th\n" +
                "   Benefit: You may increase your base speed to 20 times normal for 1 round. This increase has\n" +
                "       the side effect of multiplying your jump distance by 10 during this time period. Using\n" +
                "       this feat requires a free action and an expenditure of 8 vitality points.\n" +
                "   Knight Celerity: Instead of the normal benefits of Knight Speed, you can instead activate\n" +
                "       this feat and gain the benefits of celerity (as in Burst of Speed) as well as an additional\n" +
                "       attack at your highest attack bonus when using the full attack action.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("CONTROL", 1);
        prereqs.put("BURST OF SPEED", 1);
        prereqs.put("FLV", 7);

        setSimpleVitalityCost(8);
        setActionType("FREE");
        setIsActivatedAbility(true);
    }
}
