package com.bibfortuna.powerconverters.Feats;

public class Aware extends Feat {
    public Aware() {
        super("AWARE", "You are in harmony with your surroundings through your connection to the Force.\n" +
                    "   Prerequisite: Force Sensitive, Sense.\n" +
                    "   Benefit: +2 to Enhance Senses and See Force.\n");
    boostSkill(4, 2, true);
    boostSkill(18, 2, true);
    prereqs.put("FORCE SENSITIVE", 1);
    prereqs.put("SENSE", 1);
    }
}
