package com.bibfortuna.powerconverters.Classes;

import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;


public class Thug extends Classs {
    public Thug() {
        super("Thug",
                Attribute.STR, Attribute.DEX, Attribute.CON, Attribute.WIS, Attribute.INT, Attribute.CHA,
                8, 2,
                new ArrayList<Integer>(),//Class Skills
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},//BAB
                new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},//FORTSAVE
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//REFSAVE
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},//WILLSAVE
                new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7},//DEFENSE
                new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5},//REPUTATION
                30, 7);
        //TODO abilities and feats
        prestige = false;
        isForce = false;
        addClassSkill(4);
        addClassSkill(20);
        addClassSkill(21);
        addClassSkill(22);
        addClassSkill(28);
        addClassSkill(30);
        addClassSkill(36);
    }
}
