package com.bibfortuna.powerconverters.Feats;

public class ImprovedForceMind extends Feat {
    public ImprovedForceMind() {
        super("IMPROVED FORCE MIND", "You can grant a Force bonus to a single ability score of your allies.\n" +
                "   Prerequisite: Wisdom 15, Force Sensitive, Sense, Force Level 5.\n" +
                "   Benefit: The targets of this feat each gain a +4 Force bonus to a single ability score of\n" +
                "       your choice (all of those affected gain the bonus of the same ability score). This benefit\n" +
                "       requires a full-round action to initiate and lasts for a number of rounds equal to your Force\n" +
                "       user level. Using the Improved Force Mind feat costs 8 vitality points plus 1 pt per target.\n" +
                "       The individual using Improved Force Mind may not be one of the targets.");
        prereqs.put("WIS", 15);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("FORCE LEVEL", 5);
        prereqs.put("SENSE", 1);
        setSimpleVitalityCost(8);
    }
}
