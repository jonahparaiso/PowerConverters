package com.bibfortuna.powerconverters.Feats;

public class Fame extends Feat {
    public Fame() {
        super("FAME", "You are particularly well known.\n" +
                "   Benefit: Your Reputation bonus increases by +3.\n" +
                "   Special: You can’t select both the Fame feat and the Infamy feat. You’re either\n" +
                "       famous or infamous, not both.");
        prereqs.put("INFAMOUS", 0);//A prereq with Integer value 0 means it CAN'T have this feat
        boostMisc(3);
    }
}
