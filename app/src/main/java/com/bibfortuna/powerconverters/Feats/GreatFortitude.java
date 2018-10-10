package com.bibfortuna.powerconverters.Feats;

public class GreatFortitude extends Feat {
    public GreatFortitude() {
        super("GREAT FORTITUDE", "You are tougher than normal.\n" +
                "   Benefit: You can get a +2 synergy bonus on all Fortitude saving throws.");
        boostMisc(2);
    }
}
