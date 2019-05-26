package com.bibfortuna.powerconverters.Feats;

public class Sunder extends Feat {
    public Sunder() {
        super("SUNDER", "You are skilled at attacking an opponent’s weapon.\n" +
                "\tPrerequisite: Power Attack, Strength 13\n" +
                "\tBenefit: When you strike an opponent’s weapon in melee combat, you do not provoke an attack of\n" +
                "\t\topportunity. In addition, you gain a +4 aptitude bonus to the break check.");
        prereqs.put("POWER ATTACK",1);
        prereqs.put("STR",13);
    }
}
