package com.bibfortuna.powerconverters.Feats;

public class Stealthy extends Feat {
    public Stealthy() {
        super("","You are particularly good at avoiding notice.\n" +
                "\tBenefit: You get a +2 aptitude bonus on all Hide checks and Move Silently checks.");
        boostSkill("Hide",2);
        boostSkill("Move Silently",2);
    }
}
