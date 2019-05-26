package com.bibfortuna.powerconverters.Feats;

public class Spacer extends Feat {
    public Spacer() {
        super("SPACER", "You have a special affinity for space travel.\n" +
                "\tBenefit: You get a +2 aptitude bonus on all Astrogate checks and Pilot checks. Remember that\n" +
                "\t\tthese skills can’t be used untrained.");
        boostSkill("Astrogate",2);
        boostSkill("Pilot",2);
    }
}
