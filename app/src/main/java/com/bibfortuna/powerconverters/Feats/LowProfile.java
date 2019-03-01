package com.bibfortuna.powerconverters.Feats;

public class LowProfile extends Feat {
    public LowProfile() {
        super("LOW PROFILE", "You are less famous than others of your class and level, or you\n" +
                "   wish to maintain a less visible presence than others of your station.\n" +
                "   Benefit: You take a –2 penalty on all Reputation checks.");
        boostMisc(-2);
    }
}
