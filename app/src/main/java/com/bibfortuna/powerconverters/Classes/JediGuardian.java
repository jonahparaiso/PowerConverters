package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class JediGuardian extends Classs {

    /***Jedi Guardian***
     *  Class skills:
     *  Balance, Climb, Computer Use, Craft, Intimidate, Jump, Knowledge, Pilot, Profession, Tumble
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
     *  Vitality Die: d10 + CON mod
     *  Skill Points: 4 + INT mod
     */
    public JediGuardian() {
        super("JediGuardian",
                Attribute.STR, Attribute.CON, Attribute.WIS, Attribute.INT, Attribute.CHA, Attribute.DEX,
                10, 4,
                new ArrayList<Integer>(),//Class Skills
                new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},//BAB
                new int[] {2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//FORTSAVE
                new int[] {2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//REFSAVE
                new int[] {1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//WILLSAVE
                new int[] {3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12},//DEFENSE
                new int[] {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5},//REPUTATION
                4, 250);
        //TODO add specific abilities/feats here
        prestige = false;
        isForce = true;
        addClassSkill(2);
        addClassSkill(4);
        addClassSkill(5);
        addClassSkill(6);
        addClassSkill(20);
        addClassSkill(21);
        addClassSkill(22);
        addClassSkill(27);
        addClassSkill(28);
        addClassSkill(38);
    }


}
