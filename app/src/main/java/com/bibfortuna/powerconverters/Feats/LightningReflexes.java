package com.bibfortuna.powerconverters.Feats;

public class LightningReflexes extends Feat {
    public LightningReflexes() {
        super("LIGHTNING REFLEXES", "You have faster than normal reflexes.\n" +
                "   Benefit: You get a +2 synergy bonus on all Reflex saving throws.");
        boostMisc(2);
    }
}
