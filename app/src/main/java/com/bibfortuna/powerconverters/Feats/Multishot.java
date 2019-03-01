package com.bibfortuna.powerconverters.Feats;

public class Multishot extends Feat {
    public Multishot() {
        super("MULTISHOT", "You can use the multifire and autofire option on blaster weapons\n" +
                "       with exceptional accuracy.\n" +
                "   Prerequisite: Dexterity 13, Point Blank Shot, Rapid Shot\n" +
                "   Benefit: When using the multifire or autofire option on a blaster weapon, you reduce the\n" +
                "       penalty for each attack to –2 (for multifire) or –4 (for autofire).\n" +
                "   Normal: Without this feat, multifire has -4 penalty and autofire has -6 penalty");
        prereqs.put("DEX", 13);
        prereqs.put("POINT BLANK SHOT", 1);
        prereqs.put("RAPID SHOT", 1);
    }
}
