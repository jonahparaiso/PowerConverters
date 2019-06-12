package com.bibfortuna.powerconverters.Feats;

public class Trustworthy extends Feat {
    public Trustworthy() {
        super("TRUSTWORTHY", "You have a friendly demeanor.\n" +
                "\tBenefit: You get a +2 aptitude bonus on all Diplomacy checks and Gather Information checks.");
        boostSkill("Diplomacy",2);
        boostSkill("Gather Information", 2);
    }
}
