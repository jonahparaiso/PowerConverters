package com.bibfortuna.powerconverters.Feats;

public class GreatCleave extends Feat {
    public GreatCleave() {
        super("GREAT CLEAVE", "You can wield a melee weapon with such power that you can strike\n" +
                "   multiple times when you drop your opponents.\n" +
                "   Prerequisites: Base Attack Bonus +4, Cleave, Power Attack, Strength 13\n" +
                "   Benefit: If you deal an opponent enough damage to reduce his wounds points to below\n"+
                "       0, you get an immediate extra melee attack against another opponent in the area.\n" +
                "       You cannot take a 2-meter step before making this extra attack. The extra attack is\n" +
                "       with the same weapon and at the same bonus as the attack that dropped the previous\n" +
                "       opponent. You have no limit to the number of times you can use it per round.");
        prereqs.put("BAB", 4);
        prereqs.put("CLEAVE", 1);
        prereqs.put("POWER ATTACK", 1);
        prereqs.put("STR", 13);
        setIsActivatedAbility(true);
    }
}
