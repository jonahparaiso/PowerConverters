package com.bibfortuna.powerconverters.Feats;

public class Rugged extends Feat {
    public Rugged() {
        super("RUGGED", "You are physically tough and vigorous.\n" +
                "   Benefit: You get a +1 synergy bonus on all Fortitude saving throws and a +2 synergy\n" +
                "       bonus on all Survival checks.");
        boostMisc(1);
        boostSkill("Survival",2);
    }
}
