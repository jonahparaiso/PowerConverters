package com.bibfortuna.powerconverters.Feats;

public class Cautious extends Feat {
    public Cautious() {
        super("CAUTIOUS", "You are especially careful with tasks that may yield catastrophic results.\n" +
                    "   Benefit: +2 Demolitions and Disable Device.");
        boostSkill("Demolitions", 2);
        boostSkill("Disable Device",2);
    }
}
