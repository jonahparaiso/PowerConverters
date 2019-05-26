package com.bibfortuna.powerconverters.Feats;

public class ShotOnTheRun extends Feat {
    public ShotOnTheRun() {
        super("SHOT ON THE RUN", "You are highly trained in skirmish ranged weapon tactics.\n" +
                "\tPrerequisite: Dexterity 13, Dodge, Point Blank Shot, Mobility\n" +
                "\tBenefit: When using an attack action with a ranged weapon, you can move both before and after\n" +
                "\t\tthe attack. Provided that your total distance moved is not greater than your speed. Moving in\n" +
                "\t\tthis way does not provoke an attack of opportunity from the defender you are attacking.");
        prereqs.put("DEX",13);
        prereqs.put("DODGE",1);
        prereqs.put("POINT BLANK SHOT",1);
        prereqs.put("MOBILITY",1);
    }
}
