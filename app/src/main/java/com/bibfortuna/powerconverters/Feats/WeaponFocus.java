package com.bibfortuna.powerconverters.Feats;

public class WeaponFocus extends Feat {
    public WeaponFocus(String weapon) {
        super("WEAPON FOCUS", "Choose a specific weapon, such as blaster pistol or lightsaber." +
                "You are especially good at using this weapon. You can choose unarmed strike or grapple from your weapon for purposes of this feat.\n" +
                "\tPrerequisite: Base Attack Bonus +1, Proficient with Weapon\n" +
                "\tBenefit: You add +1 to all attack rolls you make using the selected weapon.\n" +
                "\tSpecial: You can gain this feat multiple times. Its effects do not stack. Each time you take the feat, it applies to a different weapon.");
        prereqs.put("BAB", 1);
    }
}
