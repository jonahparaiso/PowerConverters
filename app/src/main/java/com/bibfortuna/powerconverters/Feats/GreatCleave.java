package com.bibfortuna.powerconverters.Feats;

public class GreatCleave extends Feat {
    public GreatCleave() {
        super("GREAT CLEAVE", "You can wield a melee weapon with such power that you can strike\n" +
                "   multiple times when you drop your opponents.\n" +
                "   Prerequisites: Base Attack Bonus +4, Cleave, Power Attack, Strength 13\n" +
                "   Benefit: As Cleave, except that you have no limit to the number of times you can use it per round.");
        prereqs.put("BAB", 4);
        prereqs.put("CLEAVE", 1);
        prereqs.put("POWER ATTACK", 1);
        prereqs.put("STR", 13);

    }
}
