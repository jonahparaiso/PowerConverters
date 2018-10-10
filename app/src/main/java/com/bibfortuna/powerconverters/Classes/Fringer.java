package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class Fringer extends Classs {

    /***Fringer***
     *  Class skills:
     *  Climb, Craft, Gamble, Handle Animal, Hide, Jump, Knowledge, Listen, Pilot, Profession, Ride,
     *  Search, Spot, Survival, Swim
     *
     *  Starting Feats:
     *  WGP_primitive, WPG_simple
     *
     *  Bonus Feats (1st, 7th, 14th, 20th lvl choose one, must meet prereq):
     *  Alertness, Armor_Prof_Light, Endurance, Gearhead, Run, Spacer, WPG_pistols, WPG_rifles, WPG_slugthrowers
     *
     *  Abilities:
     *  BonusClassSkillS, Barter, JuryRig, Survival
     *
     *  Starting Credits:
     *  1d4 x 500
     *
     *  Vitality Die: d8 + CON mod
     *  Skill Points: 6 + INT mod
     *  */
    public Fringer() {
        super("Fringer",
                Attribute.DEX, Attribute.WIS, Attribute.STR, Attribute.INT, Attribute.CON, Attribute.CHA,
            8, 6,
                    new ArrayList<Integer>(),//Class Skills
            new int[] {0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},//BAB
            new int[] {2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//FORTSAVE
            new int[] {1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},//REFSAVE
            new int[] {0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//WILLSAVE
            new int[] {3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 11},//DEFENSE
            new int[] {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4},//REPUTATION
            4, 500);
        //TODO abilities and feats
        prestige = false;
        isForce = false;
        addClassSkill(4);
        addClassSkill(6);
        addClassSkill(16);
        addClassSkill(18);
        addClassSkill(19);
        addClassSkill(21);
        addClassSkill(22);
        addClassSkill(25);
        addClassSkill(27);
        addClassSkill(28);
        addClassSkill(30);
        addClassSkill(31);
        addClassSkill(34);
        addClassSkill(35);
        addClassSkill(36);
    }


}
