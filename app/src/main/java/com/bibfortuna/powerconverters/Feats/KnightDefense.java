package com.bibfortuna.powerconverters.Feats;

public class KnightDefense extends Feat {
    public KnightDefense() {
        super("KNIGHT DEFENSE", "You are adept at defending yourself with your lightsaber.\n" +
                "    Prerequisite: Dexterity 13, Force-Sensitive, Control, Lightsaber Defense, Exotic Weapon Proficiency (lightsaber), Jedi level 7th\n" +
                "    Benefit: When wielding a lightsaber, you gain a +2 dodge bonus to Defense.\n" +
                "    Special: A condition that makes you lose your Dexterity to Defense (if any) also makes you lose dodge bonuses.\n" +
                "        Also, dodge bonuses stack with each other, unlike most other types of bonuses.\n" +
                "        When you take this feat, your dodge bonus to Defense improves from +2 (because of Lightsaber Defense) to +4.\n" +
                "        If you also take Master Defense either before or after taking this feat, your Dodge bonus to Defense improves to +6.");
        prereqs.put("DEX", 13);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("CONTROL", 1);
        prereqs.put("LIGHTSABER DEFENSE", 1);
        prereqs.put("EXOTIC WEAPON PROFICIENCY", 1);
        prereqs.put("FLV", 7);

        boostMisc(2);
    }
}
