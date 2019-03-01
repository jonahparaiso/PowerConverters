package com.bibfortuna.powerconverters.Feats;

public class PowerAttack extends Feat {
    public PowerAttack() {
        super("POWER ATTACK", "You can make exceptionally powerful melee attacks.\n" +
                "   Prerequisite: Strength 13\n" +
                "   Benefit: On your action, before making attack rolls for a round, you may choose to subtracts a\n" +
                "       number from all melee attack rolls and add the same number to all melee damage rolls. This\n" +
                "       number may not exceed your base attack bonus. The penalty on attacks and bonus on damage applies\n" +
                "       until your next action.");
        prereqs.put("STR", 13);
        setIsActivatedAbility(true);
    }
}
