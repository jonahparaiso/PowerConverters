package com.bibfortuna.powerconverters.Feats;

import com.bibfortuna.powerconverters.Feats.Feat;

public class ImprovedInitiative extends Feat {
    public ImprovedInitiative() {
        super("IMPROVED INITIATIVE", "You can react more quickly than normal in a fight.\n" +
                "   Benefit: You get a +4 circumstance bonus on initiative checks.");
        boostMisc(4);
    }
}
