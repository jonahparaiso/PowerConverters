package com.bibfortuna.powerconverters.Feats;

public class Rage extends Feat {
    public Rage() {
        super("Rage", "You can channel your anger into a berserker fury, increasing your battle prowess\n" +
                "   as you let the dark side flow through you.\n" +
                "   Prerequisite: Control, Force-Sensitive, 2 Dark Side Points\n" +
                "   Benefit: You temporarily gain +4 Strength, +2 vitality point per Force-user level, and a +2\n" +
                "       Force bonus on Fortitude and Will saves, but you also take a –2 penalty to Defense.\n" +
                "       While raging, you can’t use skills, feats, or special abilities that require patience and concentration, such as Move Silently, Combat Expertise, Affect Mind, or any light-side Force skill.\n" +
                "\n" +
                "       Rage lasts for a number of rounds equal to 5 + your Constitution modifier. At the end\n" +
                "       of the duration, you lose the bonus vitality points and become fatigued (-2 penalty to effective Strength and Dexterity, can’t run or charge) for a number of rounds equal to the duration of the rage.\n" +
                "\n" +
                "       Special: Whenever you use this feat, you gain a Dark Side Point. The effects of Rage\n" +
                "       can’t be combined with a Wookiee rage.");
        prereqs.put("CONTROL",1);
        prereqs.put("FORCESENSITIVE",1);
        prereqs.put("DSP", 2);
        setIsActivatedAbility(true);
    }
}
