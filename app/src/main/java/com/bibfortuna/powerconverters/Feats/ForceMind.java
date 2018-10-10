package com.bibfortuna.powerconverters.Feats;

public class ForceMind extends Feat {
    public ForceMind() {
        super("FORCE MIND", "You can grant a Force bonus to a single ability score of your allies.\n" +
                "   Prerequisites: Force level 3rd, Force-Sensitive, Sense, Wisdom 15\n" +
                "   Benefit: The target of this feat each gain a +2 Force bonus to a single ability score of\n" +
                "       your choice (all those affected gain the bonus to the same ability score). This benefit\n" +
                "       requires a full-round action to initiate and lasts for a number of rounds equal to your\n" +
                "       Force-user level. Using the Force Mind feat cost 4 vitality points plus 1 vitality point\n" +
                "       per target included. The individual using Force Mind may not be one of the targets.");
        prereqs.put("FORCE LEVEL", 3);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("SENSE", 1);
        prereqs.put("WIS", 15);
        setActionType("Full round");
        setSimpleVitalityCost(4);
    }
}
