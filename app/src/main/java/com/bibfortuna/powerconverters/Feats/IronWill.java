package com.bibfortuna.powerconverters.Feats;

public class IronWill extends Feat {
    public IronWill() {
        super("IRON WILL", "You have a stronger will than normal.\n" +
                "    Benefit: You get a +2 synergy bonus on all Will saving throws.");
        boostMisc(2);
    }
}
