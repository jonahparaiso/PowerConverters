package com.bibfortuna.powerconverters.Feats;

public class ImprovedTrip extends Feat {
    public ImprovedTrip() {
        super("IMPROVED TRIP", "You are trained in tripping opponents in melee combat and follow through with an attack.\n" +
                "\n" +
                "Prerequisite: Combat Expertise, Intelligence 13\n" +
                "\n" +
                "Benefit: If you trip an opponent in melee combat, you immediately get to make a melee attack against that opponent as if you had not used your attack action for the trip attempt.\n" +
                "\n" +
                "Normal: See the normal trip rules in Combat.");
        prereqs.put("COMBAT EXPERTISE", 1);
        prereqs.put("INT", 1);
        setIsActivatedAbility(true);
    }
}
