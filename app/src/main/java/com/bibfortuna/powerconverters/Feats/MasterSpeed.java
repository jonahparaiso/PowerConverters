package com.bibfortuna.powerconverters.Feats;

public class MasterSpeed extends Feat {
    public MasterSpeed() {
        super("MASTER SPEED", "The Force allows you to move extremely fast for a brief period of time.\n" +
                "   Prerequisite: Burst of Speed, Control, Force-Sensitive, Jedi level 11th\n" +
                "   Benefit: You may increase your base speed to 30 times normal for 1 round. This increase has\n" +
                "       the side effect of multiplying your jump distance by 15 during this time period. Using this\n" +
                "       feat requires a free action and an expenditure of 12 vitality points.\n" +
                "   Master Celerity: Instead of the normal benefits of Master Speed, you can instead activate this\n" +
                "       feat and gain the benefits of celerity and knight celerity (as in Burst of Speed and Knight\n" +
                "       Speed) as well as an additional attack at your highest attack bonus when using the attack action.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("CONTROL", 1);
        prereqs.put("BURST OF SPEED", 1);
        prereqs.put("FLV", 11);

        setSimpleVitalityCost(12);
        setActionType("FREE");
        setIsActivatedAbility(true);
    }
}
