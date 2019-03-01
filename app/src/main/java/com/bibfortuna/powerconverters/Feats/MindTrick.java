package com.bibfortuna.powerconverters.Feats;

public class MindTrick extends Feat {
    public MindTrick() {
        super("MIND TRICK", "You have a knack for using the Force to fool those with weak wills.\n" +
                "   Prerequisite: Alter, Force-Sensitive\n" +
                "   Benefit: You get a +2 aptitude bonus to all Affect Mind checks and Illusion checks. Remember\n" +
                "       that the Illusion skill can’t be used untrained.");
        prereqs.put("ALTER", 1);
        prereqs.put("FORCE SENSITIVE", 1);

        boostSkill(0,2,true);
        boostSkill(16,2,true);
    }
}
