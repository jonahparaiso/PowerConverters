package com.bibfortuna.powerconverters.Feats;

public class LightsaberDefense extends Feat {
    public LightsaberDefense() {
        super("LIGHTSABER DEFENSE", "You are adept at defending yourself with your lightsaber.\n" +
                "   Prerequisite: Dexterity 13, Force-Sensitive, Control, Exotic Weapon Proficiency (lightsaber), Force level 3rd\n" +
                "   Benefit: When wielding a lightsaber, you gain a +2 dodge bonus to Defense.\n" +
                "   Special: A condition that makes you lose your Dexterity to Defense (if any) also makes\n" +
                "       you lose dodge bonuses. Also, dodge bonuses stack with each other, unlike most other\n" +
                "       types of bonuses.");
        prereqs.put("DEX", 13);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("CONTROL", 1);
        prereqs.put("EXOTIC WEAPON GROUP PROFICIENCY:LIGHTSABER", 1);
        prereqs.put("FLV", 3);

        boostMisc(2);
    }
}
