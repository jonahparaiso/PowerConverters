package com.bibfortuna.powerconverters.Feats;

public class AnimalAffinity extends Feat {
    public AnimalAffinity() {
        super("ANIMAL AFFINITY", "You are good with animals.\n" +
                    "   Benefit: +2 to Handle Animal and Ride checks.");
        boostSkill(18, 2, false);
        boostSkill(30, 2, false);
    }
}
