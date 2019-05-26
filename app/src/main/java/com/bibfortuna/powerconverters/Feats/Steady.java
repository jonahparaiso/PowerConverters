package com.bibfortuna.powerconverters.Feats;

public class Steady extends Feat {
    public Steady() {
        super("STEADY", "You are sure in movement.\n" +
                "\tBenefit: You get a+1 synergy bonus on all Reflex saving throws and a +2 synergy bonus on all Balance checks.");
        boostMisc(1);
        boostSkill("Balance",2);
    }
}
