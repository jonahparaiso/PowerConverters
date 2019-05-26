package com.bibfortuna.powerconverters.Feats;

public class Alertness extends Feat {
    public Alertness() {
        super("ALERTNESS", "You are keenly aware of your surroundings.\n" +
                "   Benefit: +2 to Listen and Spot checks.");
        boostSkill("Listen", 2);
        boostSkill("Spot", 2);
    }
}
