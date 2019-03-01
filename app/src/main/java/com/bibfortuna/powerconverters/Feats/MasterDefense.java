package com.bibfortuna.powerconverters.Feats;

public class MasterDefense extends Feat {
    public MasterDefense() {
        super("MASTER DEFENSE", "You are adept at defending yourself with your lightsaber.\n" +
                "   Prerequisite: Control, Dexterity 13, Exotic Weapon Proficiency (lightsaber), Force-Sensitive,\n" +
                "       Lightsaber Defense, Jedi level 11th\n" +
                "   Benefit: When wielding a lightsaber, you gain a +2 dodge bonus to Defense.\n" +
                "   Special: A condition that makes you lose your Dexterity to Defense (if any) also makes\n" +
                "       you lose dodge bonuses. Also, dodge bonuses stack with each other, unlike most other types of bonuses.\n" +
                "       When you take this feat, your dodge bonus to Defense improves form +2 (because of Lightsaber\n" +
                "       Defense) to +4. If you also take Knight Defense either before or after taking this feat, your\n" +
                "       Dodge bonus to Defense improves to +6.");

        prereqs.put("CONTROL", 1);
        prereqs.put("DEX", 13);
        prereqs.put("EXOTIC WEAPON PROFICIENCY:LIGHTSABER", 1);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("LIGHTSABER DEFENSE", 1);
        prereqs.put("JEDI LEVEL", 11);

        boostMisc(2);
    }
}
