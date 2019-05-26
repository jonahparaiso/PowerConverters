package com.bibfortuna.powerconverters.Feats;

public class Attuned extends Feat {
    public Attuned() {
        super("ATTUNED", "You are in harmony with yourself through your connection to the Force.\n" +
                "   Prerequisite: Force Sensitive, Control.\n" +
                "   Benefit: +2 to Enhance Ability and Heal Self.");
        boostSkill("Enhance Ability", 2);
        boostSkill("Heal Self", 2);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("CONTROL", 1);
    }
}
