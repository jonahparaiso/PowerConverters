package com.bibfortuna.powerconverters.Feats;

public class ArmorProficiencyPowered extends Feat {
    public ArmorProficiencyPowered() {
        super("ARMOR PROFICIENCY POWERED", "You understand how to fully utilize the various features of powered armor.\n" +
                "   Prerequisite: ARMOR PROFICIENCY LIGHT, ARMOR PROFICIENCY MEDIUM.\n" +
                "   Benefit: When you wear a type of armor with which you are proficient, the armor\n" +
                "       check penalty applies only to Climb, Escape Artist, Hide, Jump, Move Silently, Sleight\n" +
                "       of Hand, and Tumble checks.\n" +
                "   Normal: A character who wears armor with which she is not proficient takes an armor\n" +
                "       check penalty on attack rolls and on all skill checks that involve moving, including\n" +
                "       Ride and Pilot.");
        prereqs.put("ARMOR PROFICIENCY LIGHT", 1);
        prereqs.put("ARMOR PROFICIENCY MEDIUM", 1);
    }
}
