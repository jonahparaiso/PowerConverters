package com.bibfortuna.powerconverters.Feats;

import com.bibfortuna.powerconverters.CheckTable;

public class ForceWhirlwind extends Feat {
    public ForceWhirlwind() {
        super("FORCE WHIRLWIND", "You can use the Force to create a whirlwind of small objects\n" +
                "   that hampers concentration and deals damage.\n" +
                "   Prerequisite: Alter, Force-Sensitive, Intelligence 13, Move Object 5 ranks, Force level 6th\n" +
                "   Benefit: You can use this specialized application of Move Object to deal damage. With this feat,\n" +
                "       you wrap a swarm of small objects in the Force and whip them into a whirlwind that has a\n" +
                "       4-meter radius. Doing this requires a Move Object check (DC 20), an expenditure of 6\n" +
                "       vitality points, and a full-round action. You can take 10 when making this check, but\n" +
                "       you can’t take 20.\n\n" +
                "       All targets within the whirlwind take a –4 penalty on all attack rolls, skill checks,\n" +
                "       and ability checks as they are struck repeatedly by small objects (rocks, tools, scrap metal,\n" +
                "       or whatever else is in the area) and buffeted by a Force-generated wind. The whirlwind deals\n" +
                "       3d4 points of damage per round. A target may attempt a Reflex save (DC 20) to reduce the damage\n" +
                "       by half every round. The swarm lasts for 3 rounds.\n\n" +
                "       You can create a whirlwind up to 50 meters away from your location.");
        prereqs.put("ALTER", 1);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("INT", 13);
        prereqs.put("56", 5);
        prereqs.put("FORCE LEVEL", 6);
        setActionType("Full round");
        setCanTake10(true);

        table = new CheckTable(20,"Fail. Try improving ranks and taking ten.",
                "All targets within the whirlwind take a –4 penalty on all attack rolls, skill checks,\n" +
                "and ability checks as they are struck repeatedly by small objects (rocks, tools, scrap metal,\n" +
                "or whatever else is in the area) and buffeted by a Force-generated wind. The whirlwind deals\n" +
                "3d4 points of damage per round. A target may attempt a Reflex save (DC 20) to reduce the damage\n" +
                "by half every round. The swarm lasts for 3 rounds.\n\n" +
                "You can create a whirlwind up to 50 meters away from your location.",
                6, 6);
    }
}
