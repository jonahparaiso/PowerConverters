package com.bibfortuna.powerconverters.Feats;

public class QuickDraw extends Feat {
    public QuickDraw() {
        super("QUICK DRAW", "You can draw weapons with startling quickness.\n" +
                "   Prerequisite: Base Attack Bonus +1\n" +
                "   Benefit: You can draw a weapon as a free action instead of as a move action.");
        prereqs.put("BAB", 1);
    }
}
