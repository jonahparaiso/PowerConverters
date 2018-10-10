package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class ForceAdept extends Classs {

    /***Force Adept***
     *  Class skills:
     *  Climb, Craft, Handle Animal, Hide, Jump, Knowledge, Listen, Profession, Sense Motive, Spot,
     *  Survival, Swim, Treat Injury
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
     *  1d4 x 500
     *
     *  Vitality Die: d8 + CON mod
     *  Skill Points: 6 + INT mod
     */
    public ForceAdept() {
        super("ForceAdept",
                Attribute.INT, Attribute.WIS, Attribute.CON, Attribute.CHA, Attribute.STR, Attribute.DEX,
                8, 6,
                new ArrayList<Integer>(),//Class Skills
                new int[]{0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},//BAB
                new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//FORTSAVE
                new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//REFSAVE
                new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//WILLSAVE
                new int[]{3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 11},//DEFENSE
                new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4},//REPUTATION
                4, 500);
        //TODO abilities and feats
        prestige = false;
        isForce = true;
        addClassSkill(4);
        addClassSkill(6);
        addClassSkill(18);
        addClassSkill(19);
        addClassSkill(21);
        addClassSkill(22);
        addClassSkill(25);
        addClassSkill(28);
        addClassSkill(32);
        addClassSkill(34);
        addClassSkill(35);
        addClassSkill(36);
        addClassSkill(37);
    }
}
