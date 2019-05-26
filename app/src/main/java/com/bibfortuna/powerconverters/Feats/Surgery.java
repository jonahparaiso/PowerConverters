package com.bibfortuna.powerconverters.Feats;

import com.bibfortuna.powerconverters.CheckTable;

public class Surgery extends Feat {
    public Surgery() {
        super("SURGERY","You are trained to perform surgical procedures to heal wounds.\n" +
                "\tPrerequisites: Treat Injury 4 ranks\n" +
                "\tBenefit: You can make a Treat Injury check to heal a wounded character. You must have the proper\n" +
                "\t\tsurgical tools. You can’t take 10 or take 20 when attempting to heal wounds. You can’t perform\n" +
                "\t\tsurgery during a combat situation. You can’t restore more wound points than the character’s full normal total.\n\n" +
                "\t\tSurgery takes time; it requires 1 hour per wound point lost to perform surgery on a patient.\n" +
                "\t\tThe result of the check determines the number of wound points restored. A character who has his wounds healed\n" +
                "\t\tthrough surgery is fatigued for a number of hours equal to the number of wound points restored.");
        prereqs.put("37",4);

        table = new CheckTable(new int[]{5,10,15,20,25,30,35}, new String[]{
                "0 wounds restored","1 wounds restored","2 wounds restored","3 wounds restored","4 wounds restored",
                "5 wounds restored","6 wounds restored","7 wounds restored"},
                new int[]{0,0,0,0,0,0,0,0});
    }
}
