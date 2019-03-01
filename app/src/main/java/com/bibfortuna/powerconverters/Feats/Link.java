package com.bibfortuna.powerconverters.Feats;

public class Link extends Feat {
    public Link(){
        super("Link", "You have a knack for communicating through the Force.\n" +
                "   Prerequisite: Force-Sensitive, Sense\n" +
                "   Benefit: You get a +2 aptitude bonus to all Farseeing checks and Telepathy checks. Remember\n" +
                "       that the Farseeing skill can’t be used untrained.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("SENSE", 1);

        boostSkill(6, 2, true);
        boostSkill(19, 2, true);
    }
}
