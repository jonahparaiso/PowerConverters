package com.bibfortuna.powerconverters.Feats;

public class ImprovedTwoWeaponFighting extends Feat {
    public ImprovedTwoWeaponFighting() {
        super("IMPROVED TWO WEAPON FIGHTING", "You are an expert in fighting two-handed.\n" +
                "    Prerequisite: Ambidexterity, Base Attack Bonus +9, Two-Weapon Fighting\n" +
                "    Benefit: In addition to the standard single extra attack you get with an off-hand weapon,\n" +
                "        you get a second attack with the off-hand weapon, albeit at a –5 penalty (see Two-Weapon Fighting Penalties).\n" +
                "    Normal: Without this feat, you can only get a single extra attack with an off-hand weapon.");
        prereqs.put("AMBIDEXTERITY", 1);
        prereqs.put("TWO WEAPON FIGHTING", 1);
        prereqs.put("BAB", 9);
    }
}
