package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class TechSpecialist extends Classs {

    /***Tech Specialist***
     *  Class skills:
     *  Astrogate, Computer Use, Craft, Demolitions, Disable Device, Knowledge, Pilot, Profession,
     *  Read/Write Language, Repair, Search, Treat Injury
     *
     *  Starting Feats:
     *  TODO
     *
     *  Bonus Feats (th lvl choose one, must meet prereq):
     *  TODO
     *
     *  Abilities:
     *  TODO
     *
     *  Starting Credits:
     *  1d6 x 500
     *
     *  Vitality Die: d6 + CON mod
     *  Skill Points: 4 + INT mod
     */
    public TechSpecialist() {
        super("TechSpecialist",
                Attribute.INT, Attribute.WIS, Attribute.DEX, Attribute.CON, Attribute.CHA, Attribute.STR,
                6, 4,
                new ArrayList<Integer>(),//Class Skills
                new int[]{0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},//BAB
                new int[]{0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9},//FORTSAVE
                new int[]{1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 8, 8},//REFSAVE
                new int[]{1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8},//WILLSAVE
                new int[]{2, 3, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 8, 8, 9, 9, 9, 10},//DEFENSE
                new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5},//REPUTATION
                6, 500);
        //TODO abilities and feats
        prestige = false;
        isForce = false;
        addClassSkill(1);
        addClassSkill(5);
        addClassSkill(6);
        addClassSkill(9);
        addClassSkill(11);
        addClassSkill(22);
        addClassSkill(27);
        addClassSkill(28);
        addClassSkill(29);
        addClassSkill(31);
        addClassSkill(37);
    }

}
