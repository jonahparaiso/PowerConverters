package com.bibfortuna.powerconverters.Feats;

public class ImprovedBanthaRush extends Feat {
    public ImprovedBanthaRush() {
        super("IMPROVED BANTHA RUSH", "You know how to push opponents back.\n" +
                "   Prerequisites: Power Attack, Strength 13\n" +
                "   Benefit: When you perform a bantha rush, you do not draw an attack of opportunity from\n" +
                "       the defender. In addition, you gain a +4 aptitude bonus to the check.");
        prereqs.put("POWER ATTACK", 1);
        prereqs.put("STR", 13);
        setIsActivatedAbility(true);
    }
}
