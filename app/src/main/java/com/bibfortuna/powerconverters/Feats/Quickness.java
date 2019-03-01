package com.bibfortuna.powerconverters.Feats;

public class Quickness extends Feat {
    public Quickness(){
        super("QUICKNESS", "You are good at turning attacks that might deal damage to you into near misses and glancing blows.\n" +
                "   Benefit: You gain +3 vitality points.\n" +
                "   Special: You may gain this feat multiple times.");
        boostMisc(3);
    }
}
