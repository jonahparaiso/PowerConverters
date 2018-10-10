package com.bibfortuna.powerconverters.Feats;

public class ImprovedCritical extends Feat {
    public ImprovedCritical(String specialty, Feat weaponClass) {
        super("IMPROVED CRITICAL","Choose one type of weapon, such as a blaster pistol or a vibroblade.\n" +
                "   With that weapon, you know how to strike more effectively and have a better chance of dealing wound damage.\n" +
                "   Prerequisites: Proficient with Weapon, Base Attack Bonus +8\n" +
                "   Benefit: When using the weapon you selected, your threat range increases by 1.\n" +
                "       For example, a blaster pistol usually threatens a critical hit on a natural roll of 20.\n" +
                "       If a character using a blaster pistol has Improved Critical (blaster pistol), the threat range becomes 19-20.\n" +
                "   Special: You can gain this feat multiple times. The effects do not stack. Each time you\n" +
                "       take this feat, it applies to a different weapon.");
        prereqs.put(weaponClass.getName(), 1);
        prereqs.put("BAB", 8);
        boostMisc(1);
        setSpecialty(specialty);
    }
}
