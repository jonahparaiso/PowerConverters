package com.bibfortuna.powerconverters.Feats;

public class WeaponFinesse extends Feat {
    public WeaponFinesse(String weapon) {
        super("WEAPON FINESSE", "You are especially skilled at using a certain weapon, one that can benefit\n" +
                "as much from Dexterity as from Strength, choose one weapon from this list: cesta, combat glove,\n" +
                "double-bladed lightsaber, force pike, knife, lightsaber, unarmed strike, vibroblade, vibrodagger.\n" +
                "\tPrerequisite: Proficient with weapon, base attack bonus +1.\n" +
                "\tBenefit: With the selected weapon, you may use your Dexterity modifier instead of your Strength modifier on attack rolls.\n" +
                "\tSpecial: You can gain this feat multiple times. Each time you take the feat, it applies to a different weapon.");
        prereqs.put("BAB", 1);
        /*TODO Need to revisit once weapons are made. Perhaps instead of passing in a String, a Weapon object will be passed in,
            *  and the Weapon class will have the ability to identify its WeaponGroupProficiency, so within this scope we can call
            *
            *       prereqs.put("WEAPON GROUP PROFICIENCY", weapon.getProficiency());
            *
            *  or something along these lines*/
        prereqs.put("WEAPON GROUP PROFICIENCY", 1);
    }
}
