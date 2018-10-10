package com.bibfortuna.powerconverters.Feats;

public class CombatExpertise extends Feat {
    public CombatExpertise() {
        super("COMBAT EXPERTISE","You are trained at using your combat skill for defense as well as offense.\n" +
                "   Prerequisite: Intelligence 13\n" +
                "   Benefit: When you use the attack action or full attack action in melee, you can take\n" +
                "       a penalty of up to –5 on your attack and add the same number (up to +5) to your Defense.\n" +
                "       This number may not exceed your base attack bonus. The changes to attack rolls and Defense\n" +
                "       last until your next action. The bonus to your Defense is a dodge bonus (and as such it\n" +
                "       stacks with other dodge bonuses you may have).\n" +
                "   Normal: A character not capable of the Combat expertise feat can fight defensively\n" +
                "       while using the attack or full attack action to take a –4 penalty on attacks and gain\n" +
                "       a +2 dodge bonus to Defense.");
        prereqs.put("INT", 13);
    }
}
