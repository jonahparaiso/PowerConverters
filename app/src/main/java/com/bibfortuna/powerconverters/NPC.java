package com.bibfortuna.powerconverters;

import com.bibfortuna.powerconverters.Classes.Classs;

public class NPC extends Player {
    private int expRewarded;
    public NPC() {
        super();
        expRewarded = level;
    }
    public NPC(Species r, Classs c, int lvl, boolean printing) {
        super(r, c, lvl, printing);
        //TODO Need to figure out how much exp to reward, based on class, level, species, etc.
    }
}
