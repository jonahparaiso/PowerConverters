package com.bibfortuna.powerconverters.Feats;

public class Athletic extends Feat {
    public Athletic() {
        super("ATHLETIC", "You have a knack for athletic endeavors.\n" +
                    "   Benefit: +2 to Climb and Swim checks.");
        boostSkill("Climb", 2);
        boostSkill("Swim", 2);
    }
}
