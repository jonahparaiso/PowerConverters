package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class Scoundrel extends Classs {

    /***Scoundrel***
     *  Class skills:
     *  Appraise, Astrogate, Balance, Bluff, Computer Use, Craft, Demolitions, Disable Device, Disguise,
     *  Escape Artist, Forgery, Gamble, Gather Information, Hide, Knowledge, Listen, Sneak,
     *  Pilot, Profession, Repair, Search, Sleight of Hand, Spot, Tumble
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
     *  Skill Points: 8 + INT mod
     */
    public Scoundrel() {
        super("Scoundrel",
                Attribute.DEX, Attribute.INT, Attribute.CHA, Attribute.WIS, Attribute.CON, Attribute.STR,
                6, 8,
                new ArrayList<Integer>(),//Class Skills
                new int[]{0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},//BAB
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//FORTSAVE
                new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//REFSAVE
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//WILLSAVE
                new int[]{2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11},//DEFENSE
                new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5},//REPUTATION
                5, 500);
        //TODO abilities and feats
        prestige = false;
        isForce = false;
        addClassSkill(0);
        addClassSkill(1);
        addClassSkill(2);
        addClassSkill(3);
        addClassSkill(5);
        addClassSkill(6);
        addClassSkill(9);
        addClassSkill(11);
        addClassSkill(12);
        addClassSkill(14);
        addClassSkill(15);
        addClassSkill(16);
        addClassSkill(17);
        addClassSkill(19);
        addClassSkill(22);
        addClassSkill(25);
        addClassSkill(26);
        addClassSkill(27);
        addClassSkill(28);
        addClassSkill(29);
        addClassSkill(31);
        addClassSkill(33);
        addClassSkill(34);
        addClassSkill(38);
    }


}
