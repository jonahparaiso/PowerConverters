package com.bibfortuna.powerconverters.Feats;

public class AdvancedMartialArts extends Feat {
    public AdvancedMartialArts() {
        super("ADVANCED MARTIAL ARTS", "You are a master of unarmed combat due to your advanced training\n" +
                "   Prerequisite: Martial Arts, Improved Martial Arts, base attack bonus +8\n" +
                "   Benefit: A Medium sized creature deals 3d4 (crit 18-20) with an unarmed strike.\n" +
                "       Small creature: 3d3 (crit 18-20). Large Creature: 3d6 (crit 18-20). Str mods apply.");
        prereqs.put("MARTIAL ARTS", 1);
        prereqs.put("IMPROVED MARTIAL ARTS", 1);
        prereqs.put("BAB", 8);
    }
}
