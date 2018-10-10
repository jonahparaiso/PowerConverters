package com.bibfortuna.powerconverters.Feats;

public class Focus extends Feat {
    public Focus() {
        super("FOCUS", "You have a knack for using the Force in focused and specific ways.\n"+
                "   Prerequisite: Force-Sensitive, Alter, Control\n" +
                "   Benefit: You get a +2 aptitude bonus on all Force Stealth checks and Force Strike checks.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("ALTER", 1);
        prereqs.put("CONTROL", 1);
        boostSkill(11, 2, true);
        boostSkill(12, 2, true);
    }
}
