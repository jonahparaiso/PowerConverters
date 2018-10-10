package com.bibfortuna.powerconverters.Feats;

public class Gearhead extends Feat {
    public Gearhead() {
        super("GEARHEAD", "You have a way with machines.\n" +
                "   Benefit: You get a +2 aptitude bonus on all Repair checks and Computer Use checks.\n" +
                "       If you don’t have any ranks in Repair, this aptitude bonus can only be applied to jury-rig attempts.\n" +
                "       If you don’t have any ranks in Computer Use, this aptitude bonus can’t be applied to the break computer security use of the skill.");
        boostSkill(5,2, false);
        boostSkill(29, 2, false);
    }
}
