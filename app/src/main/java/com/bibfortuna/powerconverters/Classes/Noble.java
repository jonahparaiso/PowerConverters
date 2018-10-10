package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class Noble extends Classs {

    /***Noble***
     *  Class skills:
     *  Appraise, Computer Use, Craft, Diplomacy, Disguise, Entertain, Knowledge, Profession, Ride,
     *  Sense Motive, Speak Language, Read/Write Language
     *
     *  Starting Feats:
     *  WGP_pistols, WPG_simple
     *
     *  Bonus Feats (th lvl choose one, must meet prereq):
     *
     *  Abilities:
     *  BonusClassSkill, Favor, InspireConfidence, ResourceAccess, Coordinate, InspireGreatness
     *
     *  Starting Credits:
     *  1d4 x 1000
     *
     *  Vitality Die: d6 + CON mod
     *  Skill Points: 6 + INT mod
     */
    public Noble() {
        super("Noble",
                Attribute.CHA, Attribute.WIS, Attribute.INT, Attribute.DEX, Attribute.CON, Attribute.STR,
                6, 6,
                new ArrayList<Integer>(),//Class Skills
                new int[]{0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},//BAB
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//FORTSAVE
                new int[]{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//REFSAVE
                new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//WILLSAVE
                new int[]{2, 3, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 8, 8, 9, 9, 9, 10},//DEFENSE
                new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6},//REPUTATION
                4, 1000);
        //TODO abilities and feats
        prestige = false;
        isForce = false;
        addClassSkill(0);
        addClassSkill(5);
        addClassSkill(6);
        addClassSkill(10);
        addClassSkill(12);
        addClassSkill(13);
        addClassSkill(22);
        addClassSkill(28);
        addClassSkill(30);
        addClassSkill(32);
    }
}
