package com.bibfortuna.powerconverters.Feats;

public class DefensiveMartialArts extends Feat {
    public DefensiveMartialArts() {
        super("DEFENSIVE MARTIAL ARTS", "You are skill at avoiding harm due to your\n" +
                "   improved martial arts training.\n" +
                "   Prequisite: Martial Arts.\n" +
                "   Benefit: +2 dodge bonus to defense");
        boostMisc(2);
        prereqs.put("MARTIAL ARTS", 1);
    }
}
