package com.bibfortuna.powerconverters.Feats;

public class FarShot extends Feat {
    public FarShot() {
        super("FAR SHOT", "You can get greater distance out of a ranged weapon.\n" +
                "   Prerequisite: Point Blank Shot\n" +
                "   Benefit: When you use a blaster or a projectile weapon (such as a bow), its range\n" +
                "       increment increases by one-half (multiply by 1.5). When you use a throw weapon\n" +
                "       (such as a grenade), its range increment is doubled.");
        prereqs.put("POINT BLANK SHOT", 1);
    }
}
