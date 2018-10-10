package com.bibfortuna.powerconverters.Feats;

public class DissipateEnergy extends Feat {
    public DissipateEnergy() {
        super("DISSIPATE ENERGY", "You can resist and sometimes absorb energy damage and\n" +
                "   turn it to your advantage.\n" +
                "   Prerequisite: Control, Force-Sensitive, Force level 4th\n" +
                "   Benefit: You may dissipate or perhaps absorb energy damage you would otherwise take \n" +
                "       because of your environment or because of an attack. To do so, you make a Fortitude saving\n" +
                "       throw against a DC 10 + the amount of damage inflicted. If the saving throw is successful,\n" +
                "       you don’t take any damage and you gain 1 vitality point for every 2 points of wound damage you\n" +
                "       would have taken (but you can’t gain vitality points in excess of your full normal total). If\n" +
                "       the saving throw fails, you take the full damage.\n" +
                "   Dissipate Energy is used as a reaction, and cost no vitality points.");
        prereqs.put("CONTROL", 1);
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("FORCE LEVEL", 4);
        setActionType("Reaction");
    }
}
