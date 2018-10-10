package com.bibfortuna.powerconverters.Feats;

public class Ambidexterity extends Feat {
    public Ambidexterity() {
        super("AMBIDEXTERITY", "You are equally adept at using either hand.\n" +
                "   Prerequisite: Dexterity 15\n" +
                "   Benefit: You ignore all penalties for using an off hand. You are neither left-handed\n" +
                "       nor right-handed.\n" +
                "   Normal: Without this feat, a character who uses his or her off hand takes a –4 penalty\n" +
                "       on attack rolls, ability checks, and skill checks. For example, a right-handed character\n" +
                "       wielding a weapon with her left hand takes a –4 penalty on attack rolls with that weapon.\n" +
                "   Special: This feat helps offset the penalty for fighting with two weapons. See the\n" +
                "       Two-Weapon Fighting feat and Two-Weapon Fighting Penalties.");
        prereqs.put("DEX", 15);
    }
}
