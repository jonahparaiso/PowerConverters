package com.bibfortuna.powerconverters.Feats;

public class Compassion extends Feat {
    public Compassion() {
        super("COMPASSION", "You are concerned with the welfare of others and are connected\n" +
                "   to them through the Force.\n" +
                "   Prerequisite: Force Sensitive, Alter.\n" +
                "   Benefit: +2 to Empathy and Heal Another.");
        boostSkill("Empathy", 2);
        boostSkill("Heal Another", 2);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("ALTER", 1);
    }
}
