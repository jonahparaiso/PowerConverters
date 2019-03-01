package com.bibfortuna.powerconverters.Feats;

public class HighForceMastery extends Feat {
    public HighForceMastery() {
        super("HIGH FORCE MASTERY", "You can accomplish Force-related tasks with much less concentration than normal.\n" +
                "   Prerequisite: Wisdom 17, Force-Sensitive, Force Mastery, Force level 11th\n" +
                "   Benefit: Once per round, you may accomplish a Force-related task that normally requires a\n" +
                "       full-round action as an attack action. The vitality point cost for this action is\n" +
                "       double the normal cost (or 1 point if no cost is given).\n" +
                "   Special: This feat is not commutative with the effects of Force Mastery. That is, you\n" +
                "       cannot use both feats to reduce a full-round action to a free action.");
        prereqs.put("WIS", 17);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("FORCE MASTERY", 1);
        prereqs.put("FLV", 11);
    }
}
