package com.bibfortuna.powerconverters.Feats;

public class Mobility extends Feat {
    public Mobility() {
        super("MOBILITY", "You are skilled at dodging past opponents and avoiding attacks\n" +
                "       they make against you.\n" +
                "   Prerequisite: Dexterity 13, Dodge\n" +
                "   Benefit: You get a +4 dodge bonus to Defense against attacks of opportunity caused when\n" +
                "       you move out of, through, or within a threatened area.\n" +
                "   Special: A condition that makes you lose your Dexterity bonus to Defense (if any) also makes\n" +
                "       you lose dodge bonuses. Also, dodge bonuses stack with each other, unlike most other\n" +
                "       types of bonuses.");
        prereqs.put("DEX", 13);
        prereqs.put("DODGE", 1);
    }
}
