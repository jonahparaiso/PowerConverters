package com.bibfortuna.powerconverters;

import com.bibfortuna.powerconverters.Classes.Classs;

import java.util.ArrayList;

public class Skill {
    private Skilltype type;
    private boolean isClassSkill;
    private double ranks;
    private int miscModifier;
    private int attributeModifier;
    private int totalModifier;

    public Skill(Skilltype newSkill, boolean classSkill, int initRank, int initMisc, int initAttMod) {
        type = newSkill;
        isClassSkill = classSkill;
        ranks = initRank;
        miscModifier = initMisc;
        attributeModifier = initAttMod;
        totalModifier = (int) (ranks + miscModifier + attributeModifier);
    }

    public Skill() { new Skill(Skilltype.CLIMB, false, 0, 0, 0); }

    ////////////////////////////////////// Getters /////////////////////////////////////////////////

    public Skilltype getType() { return type; }

    public double getRank() { return ranks; }

    public int getMisc() { return miscModifier; }

    public int getTotalModifier() {
        totalModifier = (int) (ranks + miscModifier + attributeModifier);
        return totalModifier;
    }

    public int getAttributeModifier () { return attributeModifier; }

    public boolean isClassSkill() { return isClassSkill; }

    ////////////////////////////////////// Setters /////////////////////////////////////////////////

    public void setRank(int newRank) {
        this.ranks = newRank;
        this.refreshMods();
    }

    public void addRanks(double addRanks, boolean printing) {
        this.ranks += addRanks;
        if (printing) System.out.printf("leveling up %s by %.0f to %.0f %n", type.getName(), addRanks, ranks);
        this.refreshMods();
    }

    public void setMisc(int setMisc) {
        this.miscModifier = setMisc;
        this.refreshMods();
    }

    public void addMiscMod(int addMisc) {
        this.miscModifier += addMisc;
        this.refreshMods();
    }

    public void setAttributeModifier (int i) { attributeModifier = i; }

    public void setClassSkill(boolean input) { this.isClassSkill = input; }

    //////////////////////////////////////// Other /////////////////////////////////////////////////

    public int skillCheck(int roll) {
        refreshMods();
        return roll + totalModifier;
    }

    public int take10() {
        refreshMods();
        return 10 + totalModifier;
    }

    public int take20() {
        refreshMods();
        return 20 + totalModifier;
    }

    public void refreshMods() {
        //System.out.format("totalModifier was %d%n", totalModifier);
        totalModifier = (int) (ranks + miscModifier + attributeModifier);
        //System.out.format("totalModifier now is %d%n", totalModifier);
    }

    public Skill[] populateSkills(Classs c) {
        Skill[] out = new Skill[39];
        ArrayList<Integer> classSkills = c.getClassSkills();
        int i = 0;
        int j = 0;

        for (Skilltype s : Skilltype.values()) {
            if (i > 38) break;
            //System.out.printf("Adding %s to the skill array%n", s.getName());
            if (j > classSkills.size()) {
                if (classSkills.get(j).intValue() == i) {
                    //This means that the current index is in the classSkill array, meaning it's a class skill
                    out[i++] = new Skill(s, true, 0, 0, 0);
                    j++;
                    continue;
                }
            }
            out[i++] = new Skill(s, false, 0, 0, 0);
        }
        //System.out.println("Finished adding all the skills");
        return out;
    }

    public Skill[] populateForceSkills() {
        Skill[] out = new Skill[20];
        int i = 0;
        int j;
        boolean isClass;

        for (Skilltype s : Skilltype.values()) {
            if (i < 39) {
                i++;
                continue;
            }
            j = i - 39;
            //System.out.printf("Adding %s to the force skill array%n", s.getName());
            //System.out.printf("i : %d%nj : %d%n", i, j);
            if (j == 3 || j == 4 || j == 13)
                isClass = true;
            else
                isClass = false;
            out[j] = new Skill(s, isClass, 0, 0, 0);
            i++;
        }
        //System.out.println("Finished adding all the Force skills");
        return out;
    }
}


