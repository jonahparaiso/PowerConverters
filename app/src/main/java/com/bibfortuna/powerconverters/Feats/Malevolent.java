package com.bibfortuna.powerconverters.Feats;

public class Malevolent extends Feat {
    public Malevolent() {
        super("MALEVOLENT", "You have an innate connection to the dark side of the Force.\n" +
                "   Prerequisite: Alter, Force-Sensitive, Sense\n" +
                "   Benefit: You get a +2 aptitude bonus on all Fear checks and Force Grip checks.");
        prereqs.put("ALTER", 1);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("SENSE", 1);

        boostSkill("Fear", 2);
        boostSkill("Force Grip", 2);
    }
}
