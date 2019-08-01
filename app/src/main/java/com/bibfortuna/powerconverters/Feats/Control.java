package com.bibfortuna.powerconverters.Feats;

public class Control extends Feat {
    public Control() {
        super("CONTROL", "You can access your inner Force, using it to better utilize the powers of your own body.\n" +
                "\tPrerequisite: Force-Sensitive, Force Level 1st+\n" +
                "\tBenefit: You may learn Control-based Force skills. Once this feat is selected, Control-based Force skills\n" +
                "\t\tare considered to be class skills for you.\n" +
                "\tWith this feat, you can use the following skills untrained: Force Defense, Force Stealth, and Heal Self.\n" +
                "\tForce Trance: Also, you may enter a Force trance that slows your metabolism, allowing for you to survive\n" +
                "\t\tfor extended periods of time with very little air, water, or food. You may enter a trance at will;\n" +
                "\t\tdoing this requires a full-round action. It also takes a full-round action to awaken from the trance.\n" +
                "\t\tWhile in a trance, your heartbeat slows, your breathing all but ceases, and you appear to be dead.\n" +
                "\t\t(Use of the Sense-based skill See Force will identify the target as being in a trance, though the DC\n" +
                "\t\tis increased by 5.) A character in a Force trance uses only 10% as much air as a sleeping person and\n" +
                "\t\tneeds no food or water for an extended period of time. For the purposes of natural healing, a Force\n" +
                "\t\ttrance is the equivalent of assisted healing (see Healing, page 160).\n" +
                "\tWhen entering a trance, the character must declare the circumstances under which the trance will end.\n" +
                "\t\tExamples include a time limit or a certain stimulus (such as being touched). A character in a\n" +
                "\t\ttrance is not conscious of his or her surroundings and may not use any skills or abilities.\n" +
                "\tA character can remain in a trance for up to one week in a dry climate or up to one month in a wet climate\n" +
                "\t\tbefore succumbing to thirst. If a character can stay hydrated (via an intravenous drip, for example),\n" +
                "\t\the could remain in a trance for up to three months before dying of starvation.\n" +
                "\tProlong Force: Also, you may continue to use Force skills after you run out of vitality points by\n" +
                "\t\tpowering them with wound points. You must be reduced to 0 vitality points to use this option.\n" +
                "\t\tA wound point provides twice the power of a vitality point, so all cost are halved (round up, minimum of 1).\n" +
                "\t\tFor example, a Force skill that costs 5 vitality points can be powered with 3 wound points,\n" +
                "\t\tprovided you are totally out of vitality points.\n" +
                "\tNormal: With out this feat, you can’t learn Control-based Force skills.\n" +
                "\tSpecial: A character from any Force-using classes can select the feat for free with the Force\n" +
                "\t\ttraining class feature, or a Force-user can select this feat earlier, then no bonus feat is\n" +
                "\t\tgained when the character reaches the level at which the Force training would provide Control free.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("FLV", 1);
    }
}
