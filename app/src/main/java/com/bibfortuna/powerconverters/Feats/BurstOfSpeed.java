package com.bibfortuna.powerconverters.Feats;

public class BurstOfSpeed extends Feat {
    public BurstOfSpeed() {
        super("BURST OF SPEED", "Burst of Speed\n" +
                "   The Force allows you to move exceptionally fast for a brief period of time.\n" +
                "   Prerequisite: Force-Sensitive, Control, Force Level 3rd+\n" +
                "   Benefit: You may increase your base speed up to 10 times normal for 1 round. This\n" +
                "       increase has the side effect of multiplying your jump distance by 5 during this time\n" +
                "       period. Using this feat requires a free action and an expenditure of 5 vitality points.\n" +
                "   Celerity: Instead of the normal benefits of this feat, you can instead activate this\n" +
                "       feat and gain +4 meters to your base speed, +1 bonus to attack rolls, +1 dodge bonus\n" +
                "       to Defense, and a +1 bonus to Reflex saves. The vitality cost is the same as normal\n" +
                "       for this feat.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("CONTROL", 1);
        prereqs.put("FLV", 1);
        setActionType("Free action");
        setSimpleVitalityCost(5);
        setIsActivatedAbility(true);
    }
}
