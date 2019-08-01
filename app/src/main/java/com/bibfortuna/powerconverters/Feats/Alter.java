package com.bibfortuna.powerconverters.Feats;

public class Alter extends Feat {
    public Alter() {
        super("ALTER", "You can change the distribution and nature of the Force in your environment.\n" +
                "   Prerequisite: Force Sensitive, Force Level 1\n" +
                "   Benefit: You may learn Alter-based Force skills. Once this feat is selected, Alter-\n" +
                "       based Force skills are considered to be class skills for you.\n" +
                "       With this feat, you can use the following skills untrained: Affect Mind, Drain Energy,\n" +
                "       Force Grip, Force Light, Force Lightning, Force Strike, Heal Another, and Move Object.\n" +
                "       If the use of a particular skill has a Dark Side Point penalty, you gain a Dark Side\n" +
                "       Point even though you used the skill untrained.\n" +
                "   Normal: Without this feat, you can’t learn Alter-based Force skills.\n" +
                "   Special: A character from any Force-using classes can select the feat for free with\n" +
                "       the Force training class feature, or a Force-user can select this feat earlier, then\n" +
                "       no bonus feat is gained when the character reaches the level at which the Force\n" +
                "       training would provide Alter free.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("FLV", 1);
    }
}
