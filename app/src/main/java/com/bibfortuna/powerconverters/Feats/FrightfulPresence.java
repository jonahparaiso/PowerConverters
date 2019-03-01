package com.bibfortuna.powerconverters.Feats;

public class FrightfulPresence extends Feat {
    public FrightfulPresence() {
        super("FRIGHTFUL PRESENCE", "Your mere presence can terrify those around you.\n" +
                "   Prerequisites: Charisma 15, Intimidate 9 ranks\n" +
                "   Benefit: Once per round you can, as a free action, use your Frightful Presence. All\n" +
                "       opponents within 10 meters who have fewer levels than you must make a Will saving\n" +
                "       throw (DC 10 + one-half your level + your Charisma modifier). An opponent who fails\n" +
                "       his save is shaken, taking a –2 penalty on attack rolls, saves, and skill checks for\n" +
                "       a number of rounds equal to 1d6 + your Charisma modifier.\n\n" +
                "       A successful save indicates that the opponent is immune to your Frightful Presence\n" +
                "       for one day. This ability can’t affect creatures with an Intelligence of 3 or lower.\n" +
                "       If you have the Infamy feat, the Will saving throw’s DC increases by 5.\n" +
                "   Normal: A character without this feat can use the Intimidate skill (or a Charisma\n" +
                "       check if untrained) to threaten someone.");
        prereqs.put("CHA", 15);
        prereqs.put("20", 9);
        setActionType("Free action");
        setIsActivatedAbility(true);
    }
}
