package com.bibfortuna.powerconverters.Feats;

public class ExoticWeaponProficiency extends Feat {
    public ExoticWeaponProficiency(String weapon) {
        super("EXOTIC WEAPON PROFICIENCY", "Choose an exotic weapon, such as bowcaster or lightsaber.\n" +
                "   You understand how to use that type of exotic weapon in combat.\n" +
                "   Benefit: You make attack rolls with the weapon normally.\n" +
                "   Normal: A character who uses a weapon without being proficient with it takes a-4\n" +
                "       penalty on attack rolls. Some weapons may have additional penalties or drawbacks\n" +
                "       when used by a character who is not proficient.\n" +
                "   Special: You can gain this feat multiple times. Each time you take the feat, it applies\n" +
                "       to a different weapon. Some Exotic Weapons may have additional prerequisites to become proficient.\n" +
                "Proficiency with the lightsaber has a prerequisite of Dexterity 11.");
        if(weapon.contains("saber"))
            prereqs.put("DEX", 11);
        setSpecialty(weapon);
    }
}
