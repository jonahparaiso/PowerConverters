package com.bibfortuna.powerconverters.Feats;

public class Persuasive extends Feat {
    public Persuasive() {
        super("PERSUASIVE", "You have a way with words and body language.\n" +
                "   Benefit: You get a +2 aptitude bonus on all Bluff checks and Intimidate checks.");
        boostSkill("Bluff",2);
        boostSkill("Intimidate",2);
    }
}
