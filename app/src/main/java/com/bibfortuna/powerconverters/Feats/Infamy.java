package com.bibfortuna.powerconverters.Feats;

public class Infamy extends Feat {
    public Infamy(){
        super("INFAMY", "You are known for crimes or evil deeds (whether you actually committed\n" +
                "    these crimes and evil deeds or not).\n" +
                "    Benefit: Your Reputation bonus increases by +3.\n" +
                "    Special: You can’t select both the Fame feat and the Infamy feat. You’re either famous or infamous, not both.");
        prereqs.put("FAME", 0);
        boostMisc(3);
    }
}
