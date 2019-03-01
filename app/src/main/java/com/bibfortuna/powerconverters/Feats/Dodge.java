package com.bibfortuna.powerconverters.Feats;

public class Dodge extends Feat {
    public Dodge() {
        super("DODGE", "You are adept at dodging attacks.\n" +
                "   Prerequisite: Dexterity 13.\n" +
                "   Benefit: +1 dodge bonus to defense against one chosen target");
        prereqs.put("DEX", 13);
        boostMisc(1);
        setIsActivatedAbility(true);
    }
}
