package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class Soldier extends Classs {

    /***Soldier***
     *  Class skills:
     *  Astrogate, Computer Use, Craft, Demolitions, Intimidate, Knowledge, Pilot, Profession, Repair,
     *  Treat Injury
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
     *  Vitality Die: d10 + CON mod
     *  Skill Points: 4 + INT mod
     */
    public Soldier() {
        super("Soldier",
                Attribute.DEX, Attribute.CON, Attribute.STR, Attribute.INT, Attribute.WIS, Attribute.CHA,
                10, 4,
                new ArrayList<Integer>(),//Class Skills
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},//BAB
                new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//FORTSAVE
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//REFSAVE
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//WILLSAVE
                new int[]{3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12},//DEFENSE
                new int[]{0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5},//REPUTATION
                4, 500);
        //TODO abilities and feats
        prestige = false;
        isForce = false;
        addClassSkill(1);
        addClassSkill(5);
        addClassSkill(6);
        addClassSkill(9);
        addClassSkill(20);
        addClassSkill(22);
        addClassSkill(27);
        addClassSkill(28);
        addClassSkill(29);
        addClassSkill(37);
    }
}
