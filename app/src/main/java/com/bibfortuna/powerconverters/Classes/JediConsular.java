package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class JediConsular extends Classs {


    /***Jedi Consular***
     *  Class skills:
     *  Bluff, Computer Use, Craft, Diplomacy, Gather Information, Intimidate, Knowledge, Pilot,
     *  Profession, Read/Write Language, Sense Motive, Speak Language, Treat Injury
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
     *  1d4 x 250
     *
     *  Vitality Die: d8 + CON mod
     *  Skill Points: 6 + INT mod
     */
    public JediConsular() {
        super("JediConsular",
                Attribute.WIS, Attribute.CHA, Attribute.INT, Attribute.CON, Attribute.STR, Attribute.DEX,
                8, 6,
                new ArrayList<Integer>(),//Class Skills
                new int[]{0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},//BAB
                new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//FORTSAVE
                new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//REFSAVE
                new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//WILLSAVE
                new int[]{3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 11},//DEFENSE
                new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6},//REPUTATION
                4, 250);
        //TODO add specific abilities/feats here
        prestige = false;
        isForce = true;
        addClassSkill(3);
        addClassSkill(5);
        addClassSkill(6);
        addClassSkill(10);
        addClassSkill(17);
        addClassSkill(20);
        addClassSkill(22);
        addClassSkill(27);
        addClassSkill(28);
        addClassSkill(32);
        addClassSkill(37);
    }
}
