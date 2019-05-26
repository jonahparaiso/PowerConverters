package com.bibfortuna.powerconverters.Feats;

public class Headstrong extends Feat {
    public Headstrong() {
        super("HEADSTRONG", "You are obstinate and unwilling to yield.\n" +
                "   Benefit: You are a +1 synergy bonus on all Will saving throws and a +2 synergy bonus on all Intimidate checks.");
        boostMisc(1);
        boostSkill("Intimidate", 2);
    }
}
