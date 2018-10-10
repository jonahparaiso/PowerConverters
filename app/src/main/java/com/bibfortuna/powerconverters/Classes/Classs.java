package com.bibfortuna.powerconverters.Classes;


import com.bibfortuna.powerconverters.Attribute;

import java.util.ArrayList;

public class Classs {
    public String name;
    public int classLvl;
    public boolean prestige;
    public boolean isForce;

    /**
     * Certain classes favor different attributes over others, for example it's advantageous for Nobles to
     * have high Charisma as most of their class skills and gameplay revolve around high Charisma. Of course
     * it is nice to have high scores overall, but for intelligent hero random generation, priorities must exist*/
    private final Attribute fav1;
    private final Attribute fav2;
    private final Attribute fav3;
    private final Attribute fav4;
    private final Attribute fav5;
    private final Attribute fav6;

    /**
     * When a hero levels up, they gain an amount of vitality dependent on their class. For example,
     * when a Jedi Guardian levels up, they roll a d8 and add their CON modifier to the result to see
     * how much new vitality they get. A Scout or Noble however would only roll a d6, and a Noble would
     * roll a d6 rather than a d8.
     *
     * A similar concept applies for getting new skills, except no die is rolled, rather they gain a
     * constant number plus their INT modifier. A Fringer gets 6 + INT mod compared to a Soldier getting
     * only 4 + INT mod.
     */
    private final int vitalityUp;
    private final int skillUp;

    /**
     * This is NOT actually the array of skills, rather an array of ints that point to which skills
     * are their class skills. For example, a Jedi Consular naturally can Treat Injury, so somewhere
     * in their classSkills[] is the number 37, which points to Treat Injury. A later method will use
     * this array to initialize all the class skills in the real Skill[];
     */
    private ArrayList<Integer> classSkills;

    //private Ability[] abilities;TODO
    //private Feat[] feats;TODO

    /**
     * Array of ints that represent the Base Attack Bonus for a hero at a given level. For example, at
     * level 4, the BAB for a hero will be BAB[3]. Index is 3 because we start at 0, CS duh.
     */
    private final int[] BAB;

    /**
     * Just like the BAB array above, the same exact concept is used for the base saves, class defense
     * bonus, and reputation.
     */
    private final int[] fortSave;
    private final int[] refSave;
    private final int[] willSave;

    private final int[] defense;
    private final int[] reputation;

    /**
     * The starting credits of a hero is as such:
     * credits = cash * Dice.roll(cashDie);
     * This happens at the Player level, not here in the Classs.
     */
    private final int cash;
    private final int cashDie;

    Classs(String s, Attribute f1, Attribute f2, Attribute f3, Attribute f4, Attribute f5, Attribute f6,
           int vtUp, int skUp, ArrayList<Integer> cSkills, /*Ability[] abilities, Feat[] feats*/ int[] bab,
           int[] fort, int[] ref, int[] will, int[] def, int[] rep, int monayDie, int monay) {
        this.name = s;
        this.classLvl = 0;
        this.fav1 = f1;
        this.fav2 = f2;
        this.fav3 = f3;
        this.fav4 = f4;
        this.fav5 = f5;
        this.fav6 = f6;

        this.vitalityUp = vtUp;
        this.skillUp = skUp;
        this.classSkills = cSkills;
        //this.abilities = abilities;TODO
        //this.feats = feats;TODO
        this.BAB = bab;
        this.fortSave = fort;
        this.refSave = ref;
        this.willSave = will;
        this.defense = def;
        this.reputation = rep;
        this.cash = monay;
        this.cashDie = monayDie;
    }

    public String getName() { return name; }
    public int getClassLvl() { return classLvl; }
    public void classLvlUp() { classLvl++; }

    public Attribute getFav1() {
        return fav1;
    }
    public Attribute getFav2() {
        return fav2;
    }
    public Attribute getFav3() {
        return fav3;
    }
    public Attribute getFav4() {
        return fav4;
    }
    public Attribute getFav5() {
        return fav5;
    }
    public Attribute getFav6() {
        return fav6;
    }

    public int getVitalityUp() {
        return vitalityUp;
    }
    public int getSkillUp() {
        return skillUp;
    }

    public ArrayList<Integer> getClassSkills() {
        return classSkills;
    }
    public int[] getBAB() {
        return BAB;
    }
    public int[] getFortSave() {
        return fortSave;
    }
    public int[] getRefSave() {
        return refSave;
    }
    public int[] getWillSave() {
        return willSave;
    }
    public int[] getDefense() {
        return defense;
    }
    public int[] getReputation() {
        return reputation;
    }

    public int getBABValue(int i) { return BAB[i-1]; }
    public int getFortSaveValue(int i) {
        return fortSave[i-1];
    }
    public int getRefSaveValue(int i) {
        return refSave[i-1];
    }
    public int getWillSaveValue(int i) {
        return willSave[i-1];
    }
    public int getDefenseValue(int i) {
        return defense[i-1];
    }
    public int getReputationValue(int i) {
        return reputation[i-1];
    }

    public int getCash() {
        return cash;
    }
    public int getCashDie() { return cashDie; }

    public void addClassSkill(int i) {
        classSkills.add(new Integer(i));
    }
}
