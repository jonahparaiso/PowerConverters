package com.bibfortuna.powerconverters.Feats;

public class ArmorProficiencyMedium extends Feat {
    public ArmorProficiencyMedium() {
        super("ARMOR PROFICIENCY MEDIUM", "You are proficient with medium armor.\n" +
                "   Prerequisite: ARMOR PROFICIENCY LIGHT\n" +
                "   Benefit: When you wear a type of armor with which you are proficient, the armor\n" +
                "       check penalty applies only to Climb, Escape Artist, Hide, Jump, Move Silently, Sleight\n" +
                "       of Hand, and Tumble checks.\n" +
                "   Normal: A character who wears armor with which she is not proficient takes an armor\n" +
                "       check penalty on attack rolls and on all skill checks that involve moving, including\n" +
                "       Ride and Pilot.");
        prereqs.put("ARMOR PROFICIENCY LIGHT", 1);

    }
}
