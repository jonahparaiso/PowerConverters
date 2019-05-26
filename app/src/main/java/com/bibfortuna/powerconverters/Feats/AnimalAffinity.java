package com.bibfortuna.powerconverters.Feats;

public class AnimalAffinity extends Feat {
    public AnimalAffinity() {
        super("ANIMAL AFFINITY", "You are good with animals.\n" +
                    "   Benefit: +2 to Handle Animal and Ride checks.");
        boostSkill("Handle Animal", 2);
        boostSkill("Ride", 2);
    }
}
