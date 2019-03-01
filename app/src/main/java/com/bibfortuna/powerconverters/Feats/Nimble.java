package com.bibfortuna.powerconverters.Feats;

public class Nimble extends Feat {
    public Nimble() {
        super("NIMBLE", "You have exceptional flexibility and manual dexterity.\n" +
                "   Benefit: You get a +2 aptitude bonus on all Escape Artist checks and Sleight of Hand\n" +
                "       checks. Remember that the Sleight of Hand skill can’t be used untrained.");
        boostSkill(13,2,false);
        boostSkill(33,2,false);
    }
}
