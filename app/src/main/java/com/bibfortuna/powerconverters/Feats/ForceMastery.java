package com.bibfortuna.powerconverters.Feats;

public class ForceMastery extends Feat {
    public ForceMastery() {
        super("FORCE MASTERY","You can accomplish Force-related tasks with less concentration than normal.\n" +
                "   Prerequisite: Force-Sensitive, Force level 7th, Wisdom 15\n" +
                "   Benefit: Once per round, you may accomplish a Force-related task that normally requires an\n" +
                "       attack or move action as a free action. The vitality cost for this action is double the\n" +
                "       normal cost (or 1 point if no cost is given)\n" +
                "   Special: This feat is not cumulative with the effects of High Force Mastery. That is, you\n" +
                "       cannot use both feats to reduce a full-round action to a free action.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("FLV", 7);
        prereqs.put("WIS", 15);
        setIsActivatedAbility(true);
    }
}
