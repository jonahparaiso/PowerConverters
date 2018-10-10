package com.bibfortuna.powerconverters.Feats;

public class Athletic extends Feat {
    public Athletic() {
        super("ATHLETIC", "You have a knack for athletic endeavors.\n" +
                    "   Benefit: +2 to Climb and Swim checks.");
        boostSkill(4, 2, false);
        boostSkill(36, 2, false);
    }
}
