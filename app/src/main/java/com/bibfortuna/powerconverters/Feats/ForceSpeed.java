package com.bibfortuna.powerconverters.Feats;

public class ForceSpeed extends Feat {
    public ForceSpeed() {
        super("FORCE SPEED", "The Force allows you to move extremely fast for a brief period of time.\n" +
                "   Prerequisites: Burst of Speed, Control, Force-Sensitive, Force level 7th\n" +
                "   Benefit: Your base speed becomes 20 times normal for 1 round. This benefit has the\n" +
                "       side effect of multiplying your jump distance by 10 during this time period. Using\n" +
                "       this feat requires a free action and an expenditure of 8 vitality points.\n" +
                "   Force Celerity: Instead of the normal benefits of Force Speed, you can instead activate\n" +
                "       this feat and gain the benefits of celerity (as in Burst of Speed) as well as an\n" +
                "       additional attack at your highest attack bonus when using the full attack action.");
        prereqs.put("BURST OF SPEED", 1);
        prereqs.put("CONTROL", 1);
        prereqs.put("FORCE LEVEL", 7);
        setActionType("Free action");
        setSimpleVitalityCost(8);
    }
}
