package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class Scout extends Classs {
    /***Scout***
     *  Class skills:
     *  Astrogate, Climb, Computer Use, Craft, Demolition, Disguise, Hide, Jump, Knowledge, Listen,
     *  Sneak, Pilot, Profession, Read/Write Language, Repair, Ride, Search, Speak Language,
     *  Spot, Survival, Swim
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
     *  Vitality Die: d8 + CON mod
     *  Skill Points: 6 + INT mod
     */
    public Scout() {
        super("Scout",
                Attribute.DEX, Attribute.INT, Attribute.WIS, Attribute.STR, Attribute.CON, Attribute.CHA,
                8, 6,
                new ArrayList<Integer>(),//Class Skills
                new int[]{0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},//BAB
                new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//FORTSAVE
                new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//REFSAVE
                new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//WILLSAVE
                new int[]{2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11},//DEFENSE
                new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5},//REPUTATION
                4, 500);
        //TODO abilities and feats
        prestige = false;
        isForce = false;
        addClassSkill(1);
        addClassSkill(4);
        addClassSkill(5);
        addClassSkill(6);
        addClassSkill(9);
        addClassSkill(12);
        addClassSkill(19);
        addClassSkill(21);
        addClassSkill(22);
        addClassSkill(25);
        addClassSkill(26);
        addClassSkill(27);
        addClassSkill(28);
        addClassSkill(29);
        addClassSkill(30);
        addClassSkill(31);
        addClassSkill(34);
        addClassSkill(35);
        addClassSkill(36);
    }
}
