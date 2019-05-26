package com.bibfortuna.powerconverters.Feats;

public class RapidShot extends Feat {
    public RapidShot() {
        super("RAPID SHOT", "You can use ranged weapons with exceptional quickness.\n" +
                "   Prerequisite: Dexterity 13, Point Blank Shot\n" +
                "   Benefit: You can get one extra attack per found with a ranged weapon. The attack is at your\n" +
                "       highest base attack bonus, but each attack (the extra one and the normal ones) has a –2\n" +
                "       penalty. You must use a full-round action to use this feat.");
        prereqs.put("DEX", 13);
        prereqs.put("POINT BLANK SHOT", 1);
        setActionType("Full");
        setIsActivatedAbility(true);
    }
}
