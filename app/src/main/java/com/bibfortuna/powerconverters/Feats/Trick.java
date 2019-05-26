package com.bibfortuna.powerconverters.Feats;

public class Trick extends Feat {
    public Trick() {
        super("TRICK", "Trick\n" +
                "/tYou are cunning and deceptive.\n" +
                "\n" +
                "/tBenefit: You get a +2 aptitude bonus on all Bluff checks and Gamble checks.");
        boostSkill("Bluff",2);
        boostSkill("Gamble",2);
    }
}
