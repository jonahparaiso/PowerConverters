package com.bibfortuna.powerconverters.Feats;

public class ImprovedDisarm extends Feat {
    public ImprovedDisarm() {
        super("IMPROVED DISARM","You know how to disarm opponents in melee combat.\n" +
                "   Prerequisites: Combat Expertise, Intelligence 13\n" +
                "   Benefit: You do not draw an attack of opportunity when you attempt to disarm an opponent,\n" +
                "       nor does the opponent get a chance to disarm you. In addition, you gain a +4 aptitude\n" +
                "       bonus on disarm checks.\n" +
                "   Normal: See the normal disarm rules in combat.");
        prereqs.put("COMBAT EXPERTISE", 1);
        prereqs.put("INT", 13);
    }
}
