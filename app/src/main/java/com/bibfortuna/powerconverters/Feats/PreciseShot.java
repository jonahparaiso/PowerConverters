package com.bibfortuna.powerconverters.Feats;

public class PreciseShot extends Feat {
    public PreciseShot() {
        super("PRECISE SHOT", "You are skilled at timing and aiming ranged attacks.\n" +
                "   Prerequisite: Point Blank Shot\n" +
                "   Benefit: You can shoot or throw ranged weapons at an opponent engaged in melee without taking\n" +
                "       the standard –4 penalty (see Shooting or Throwing into a Melee).");
        prereqs.put("POINT BLANK SHOT", 1);
    }
}
