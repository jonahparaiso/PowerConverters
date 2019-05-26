package com.bibfortuna.powerconverters.Feats;

public class SpringAttack extends Feat {
    public SpringAttack() {
        super("SPRING ATTACK", "You are trained in fast melee attacks and fancy footwork.\n" +
                "\tPrerequisite: Base Attack Bonus +4, Dexterity 13, Dodge, Mobility\n" +
                "\tBenefit: When using an attack action whit a melee weapon, you can move both before and after\n" +
                "\t\tthe attack, provided that your total distance moved is not greater than your speed. Moving in\n" +
                "\t\tthis way does not provoke an attack of opportunity from the defender you are attacking. You can’t\n" +
                "\t\tuse this feat if you are carrying a heavy load or wearing heavy armor.");
        prereqs.put("BAB",4);
        prereqs.put("DEX",13);
        prereqs.put("DODGE",1);
        prereqs.put("MOBILITY",1);
    }
}
