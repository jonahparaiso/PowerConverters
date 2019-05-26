package com.bibfortuna.powerconverters.Feats;

public class Toughness extends Feat {
    public Toughness() {
        super("TOUGHNESS", "You are tougher than normal.\n" +
                "\tBenefit: You gain +3 wound points.\n" +
                "\tSpecial: A character may gain this feat multiple times.");
        boostMisc(3);
    }
}
