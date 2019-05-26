package com.bibfortuna.powerconverters.Feats;

public class Acrobatic extends Feat {
    public Acrobatic() {
        super ("ACROBATIC", "You are very agile.\n" +
                "   Benefit: +2 to Jump and Tumble checks.");
        boostSkill("Jump", 2);
        boostSkill("Tumble", 2);
    }
}
