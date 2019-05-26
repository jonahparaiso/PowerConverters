package com.bibfortuna.powerconverters.Feats;

public class SkillEmphasis extends Feat {
    public SkillEmphasis(String skill){
        super("SKILL EMPHASIS", "Choose a skill, such as Repair. You have a special knack with that skill.\n" +
                "\tBenefit: You get a +3 competence bonus on all skill checks with that skill.\n" +
                "\tSpecial: You can gain this feat multiple times. Its effects do not stack. Each time you take the\n" +
                "\t\tfeat, it applies to a different skill. This bonus does not allow you to make checks for trained-only\n" +
                "\t\tskill if you have no ranks in the skill.");
        boostSkill(skill, 3);
    }
}
