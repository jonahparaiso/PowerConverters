package com.bibfortuna.powerconverters.Feats;

public class Mimic extends Feat {
    public Mimic() {
        super("MIMIC", "You have a gift for impersonation.\n" +
                "   Benefit: You get a +2 aptitude bonus on all Disguise checks and on all Entertain checks\n" +
                "      pertaining to comedy, drama, impersonation, and story telling.");
        boostSkill("Disguise",2);
        boostSkill("Entertain",2);
    }
}
