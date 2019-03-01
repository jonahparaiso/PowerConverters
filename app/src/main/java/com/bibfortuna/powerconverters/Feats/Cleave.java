package com.bibfortuna.powerconverters.Feats;

public class Cleave extends Feat {
    public Cleave() {
        super("CLEAVE", "You can follow through with a powerful melee attack.\n" +
                "   Prerequisite: Strength 13, Power Attack\n" +
                "   Benefit: If you deal an opponent enough damage to reduce his wounds points to below\n" +
                "       0, you get an immediate extra melee attack against another opponent in the area.\n" +
                "       You cannot take a 2-meter step before making this extra attack. The extra attack is\n" +
                "       with the same weapon and at the same bonus as the attack that dropped the previous\n" +
                "       opponent. You can use this ability once per round.");
        prereqs.put("POWER ATTACK", 1);
        prereqs.put("STR", 13);
        setIsActivatedAbility(true);
    }
}
