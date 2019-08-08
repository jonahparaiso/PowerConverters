package com.bibfortuna.powerconverters.Feats;

public class Sense extends Feat {
    public Sense() {
        super("SENSE", "You can sense the Force that binds and connects all things.\n" +
                "\tPrerequisite: Force-Sensitive, Force level 1st\n" +

                "\tBenefit: You may learn Sense-based Force skills. Once this feat is selected, Sense-based Force skills are considered\n" +
                "\t\tto be class skills for you.\n" +
                "\tWith this feat, you can use the following skills untrained: Enhance Senses, Fear, See Force, and Telepathy\n" +

                "\tSense Force: A character with this feat sometimes receives vague impressions of the Force. The character might feel\n" +
                "\t\tuneasy due to an unseen situation or sense the presence of powerful emanations of the dark side. For example, a\n" +
                "\t\tcharacter might feel a sense of cold from a place that is strong in the dark side of the Force, or he might\n" +
                "\t\texperience foreboding when an entire planet is destroyed and all the life forms on it are extinguished. If\n" +
                "\t\ttwo characters with this feat have a close relationship (they are siblings, lovers, or lifelong friends, for\n" +
                "\t\texample), one of them can sense if the other one is injured or in distress. These sensations are not felt\n" +
                "\t\tthrough conscious effort: the GM provides the impressions when appropriate.\n" +

                "\tSense Surroundings: Any character with the Sense feat can extend his senses through the Force to gain an\n" +
                "\t\tawareness of his surroundings. Such awareness allows the character to fight and make skill checks\n" +
                "\t\t(including Listen and Spot checks) without taking any penalties for darkness or a lack of line of sight. This\n" +
                "\t\tability does not duplicate the Enhance Senses skill, but rather allows a character to perceive normally through\n" +
                "\t\tthe Force instead of through normal sense, thus compensating for blindness or deafness.\n" +
                "\tActivating this awareness requires a movement action. Using it costs 2 vitality points, and the ability\n" +
                "\t\tcompensates for only one sense at a time. If a Force-User wishes to use this ability to overcome both\n" +
                "\t\tblindness and deafness in the same round, for example, he would need to spend 4 vitality points and one movement action to do so.\n" +

                "\tNormal: Without this feat, you can’t learn Sense-based skills.\n" +

                "\tSpecial: A character from any of the Force-using classes can select this feat for free with the Force training\n" +
                "\t\tclass feature, or a Force-user can select this feat earlier at the expense of some other feat. If Sense is\n" +
                "\t\tselected earlier, then no bonus feat is gained when the character reaches the level at which Force training\n" +
                "\t\twould provide Sense for free.");
        prereqs.put("FORCE SENSITIVE", 1);
        prereqs.put("FLV", 1);
    }
}
