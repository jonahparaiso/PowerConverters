package com.bibfortuna.powerconverters.Feats;

public class KnightMind extends Feat {
    public KnightMind() {
        super("KNIGHT MIND", "You can grant a Force bonus to a single ability score of your allies.\n" +
                "   Prerequisite: Wisdom 15, Force-Sensitive, Sense, Force Mind, Jedi level 7th\n" +
                "   Benefit: The targets of this feat each gain a +6 Force bonus to a single ability score of your choice\n" +
                "       (all those affected gain the bonus to the same ability score). This benefit requires a full-round\n" +
                "       action to initiate and lasts for a number of rounds equal to your Force-user level. Using the Knight\n" +
                "       Mind feat costs 12 vitality points plus 1 vitality point per target included. The individual using\n" +
                "       Knight Mind may not be one of the targets.");
        prereqs.put("WIS", 15);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("SENSE", 1);
        prereqs.put("FORCE MIND", 1);
        prereqs.put("FLV", 7);

        setActionType("Full round");
        setIsActivatedAbility(true);
    }
}
