package com.bibfortuna.powerconverters.Feats;

public class SharpEyed extends Feat {
    public SharpEyed() {
        super("SHARP EYED", "You have an eye for detail.\n" +
                "   Benefit: You get a +2 aptitude bonus on all Search checks and Sense Motive checks.");
        boostSkill("Search",2);
        boostSkill("Sense Motive",2);
    }
}
