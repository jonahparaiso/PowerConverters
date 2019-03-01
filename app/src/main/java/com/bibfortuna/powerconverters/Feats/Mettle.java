package com.bibfortuna.powerconverters.Feats;

public class Mettle extends Feat {
    public Mettle() {
        super("METTLE", "You have courage and the ability to face any threat through your\n" +
                "   connection to the Force.\n" +
                "   Prerequisite: Control, Force-Sensitive\n" +
                "   Benefit: You get a +2 aptitude bonus to all Battlemind checks and Force Defense checks.\n" +
                "       Remember that the Battlemind skill can’t be used untrained.");
        prereqs.put("CONTROL",1);
        prereqs.put("FORCE SENSITIVE", 1);

        boostSkill(1,2,true);
        boostSkill(8,2,true);
    }
}
