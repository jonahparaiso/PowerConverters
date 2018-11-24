package com.bibfortuna.powerconverters.Feats;

public class ImprovedMartialArts extends Feat {
    public ImprovedMartialArts(){
        super("IMPROVED MARTIAL ARTS", "You are more skilled at unarmed attacks due to your improved training.\n" +
                "    Prerequisite: Base Attack Bonus +4, Martial Arts\n" +
                "    Benefit: A Medium-size character deals 2d4 damage with an unarmed strike. Strength modifiers apply. Also, \n" +
                "        you threaten a critical hit on a natural 19 or 20 when making an unarmed attack.\n" +
                "        Small characters deal 2d3 points of damage and Large characters deal 2d6 points of damage \n" +
                "        with an unarmed strike and this feat.");
        prereqs.put("BAB", 4);
        prereqs.put("MARTIAL ARTS", 1);
    }
}
