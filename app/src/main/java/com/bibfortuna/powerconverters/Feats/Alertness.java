package com.bibfortuna.powerconverters.Feats;

public class Alertness extends Feat {
    public Alertness() {
        super("ALERTNESS", "You are keenly aware of your surroundings.\n" +
                "   Benefit: +2 to Listen and Spot checks.");
        boostSkill(25, 2, false);
        boostSkill(34, 2, false);
    }
}
