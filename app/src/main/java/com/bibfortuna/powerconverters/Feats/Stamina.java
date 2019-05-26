package com.bibfortuna.powerconverters.Feats;

public class Stamina extends Feat {
    public Stamina() {
        super("STAMINA", "You have an amazing amount of stamina.\n" +
                "\tPrerequisite: Constitution 13\n" +
                "\tBenefit: You recover vitality points twice as fast as normal. So, if you would normally recover 1\n" +
                "\t\tvitality point per level per hour, with this feat you recover 2 vitality points per level per hour.\n" +
                "\t\tA Wookiee character with this feat recovers 4 vitality points per level per hour.");
        prereqs.put("CON",13);
    }
}
