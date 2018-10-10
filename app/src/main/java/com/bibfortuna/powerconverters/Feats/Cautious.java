package com.bibfortuna.powerconverters.Feats;

public class Cautious extends Feat {
    public Cautious() {
        super("CAUTIOUS", "You are especially careful with tasks that may yield catastrophic results.\n" +
                    "   Benefit: +2 Demolitions and Disable Device.");
        boostSkill(9, 2, false);
        boostSkill(11,2, false);
    }
}
