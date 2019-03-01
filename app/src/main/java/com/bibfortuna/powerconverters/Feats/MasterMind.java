package com.bibfortuna.powerconverters.Feats;

public class MasterMind extends Feat {
    public MasterMind() {
        super("MASTER MIND", "You can grant a Force bonus to a single ability score of your allies.\n" +
                "   Prerequisite: Force Mind, Force-Sensitive, Sense, Wisdom 15, Jedi level 11th\n" +
                "   Benefit: The targets of this feat each gain a +8 Force bonus to a single ability score of your\n" +
                "       choice (all those affected gain the bonus to the same ability score). This benefit requires\n" +
                "       a full-round action to initiate and lasts for a number of rounds equal to your Force-user\n" +
                "       level. Using the Master Mind feat costs 16 vitality points plus 1 vitality point per target\n" +
                "       included. The individual using Master Mind may not be one of the targets.");

        prereqs.put("FORCE MIND", 1);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("SENSE", 1);
        prereqs.put("WIS", 15);
        prereqs.put("FLV", 11);

        setActionType("FULL");
        setIsActivatedAbility(true);
    }
}
