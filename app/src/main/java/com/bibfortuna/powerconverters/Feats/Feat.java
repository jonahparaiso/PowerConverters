package com.bibfortuna.powerconverters.Feats;

import com.bibfortuna.powerconverters.CheckTable;
import java.util.HashMap;

public class Feat {
    /**
     * These arrays represent the skill and force skill arrays for a hero. If there is a number in one
     * of these arrays, that means the skill at that index will receive a bonus equal to the amount in
     * the cell.
     *
     * For example, if skillboost[20] == 2, that means that there is a +2 bonus to the Intimidate (20)
     * skill.
     */
    protected HashMap<String,Integer> prereqs;

    private final String name;
    private final String info;
    private boolean isActivatedAbility;
    private String actionType;
    private String specialty;
    protected CheckTable table;

    private int miscBonus;

    private HashMap<String,Integer>  skillsBoosted;
    private boolean skillsAreBoosted;
    private boolean fSkillsAreBoosted;
    private boolean canTake10;
    private int simpleVitalityCost;
    private boolean initialized;

    public Feat(String name, String info) {
        this.name = name;
        this.info = info;
        this.isActivatedAbility = false;
        this.miscBonus = 0;
        this.skillsBoosted = new HashMap<>();
        this.prereqs = new HashMap<>();
        this.actionType = "";
        this.specialty = "";
        this.initialized = false;
    }

    //////////////////////////////////////////GETTERS///////////////////////////////////////////////

    public HashMap getPrereqs() { return prereqs; }

    public String getName() {
        return name;
    }
    public String getInfo() {
        return info;
    }
    public boolean isActivatedAbility() { return isActivatedAbility; }
    public String getType() { return actionType; }
    public String getSpecialty() { return specialty; }
    public String checkTable(int roll) { return table.makeCheck(roll); }
    public CheckTable getTable() { return table; }

    public int getMiscBonus() { return miscBonus; }

    public int[] getSkills() { return  skillsBoosted; }
    public int[] getForceSkills() { return  fSkillsBoosted; }
    public boolean areSkillsBoosted() { return skillsAreBoosted; }
    public boolean areFSkillsBoosted() { return  fSkillsAreBoosted; }
    public boolean getCanTake10() { return canTake10; }
    public int getSimpleVitalityCost() { return simpleVitalityCost; }
    public boolean isInitialized() { return initialized; }

    public int getSkillIndex(String skill) {
        String s = skill.toLowerCase().replaceAll("\\s","");
        switch (s) {
            case "appraise":
                return 0;
            case "astrogate":
                return 1;
            case "balance":
                return 2;
            case "bluff":
                return 3;
            case "climb":
                return 4;
            case "computeruse":
                return 5;
            case "craft1":
                return 6;
            case "craft2":
                return 7;
            case "craft3":
                return 8;
            case "demolitions":
                return 9;
            case "diplomacy":
                return 10;
            case "disabledevice":
                return 11;
            case "disguise":
                return 12;
            case "entertain":
                return 13;
            case "escapeartist":
                return 14;
            case "forgery":
                return 15;
            case "gamble":
                return 16;
            case "gatherinformation":
                return 17;
            case "handleanimal":
                return 18;
            case "hide":
                return 19;
            case "intimidate":
                return 20;
            case "jump":
                return 21;
            case "knowledge1":
                return 22;
            case "knowledge2":
                return 23;
            case "knowledge3":
                return 24;
            case "listen":
                return 25;
            case "movesilently":
                return 26;
            case "pilot":
                return 27;
            case "profession":
                return 28;
            case "repair":
                return 29;
            case "ride":
                return 30;
            case "search":
                return 31;
            case "sensemotive":
                return 32;
            case "sleightofhand":
                return 33;
            case "spot":
                return 34;
            case "survival":
                return 35;
            case "swim":
                return 36;
            case "treatinjury":
                return 37;
            case "tumble":
                return 38;
            case "affectmind":
                return 39;
            case "battlemind":
                return 40;
            case "drainenergy":
                return 41;
            case "empathy":
                return 42;
            case "enhanceability":
                return 43;
            case "enhancesenses":
                return 44;
            case "farseeing":
                return 45;
            case "fear":
                return 46;
            case "forcedefence":
                return 47;
            case "forcegrip":
                return 48;
            case "forcelightning":
                return 49;
            case "forcestealth":
                return 50;
            case "forcestrike":
                return 51;
            case "friendship":
                return 52;
            case "healanother":
                return 53;
            case "healself":
                return 54;
            case "illusion":
                return 55;
            case "moveobject":
                return 56;
            case "seeforce":
                return 57;
            case "telepathy":
                return 58;
            default:
                System.out.println("ERROR converting String Skill into an int index");
        }
    }


    //////////////////////////////////////////SETTERS///////////////////////////////////////////////

    protected void boostSkill(String s, int boost) {
        int index = getSkillIndex(s);
        if (index < 39) {
            skillsBoosted = new int[39];
            skillsBoosted[index] = boost;
            skillsAreBoosted = true;
        }
        else {
            index -= 38;
            fSkillsBoosted = new int[20];
            fSkillsBoosted[index] = boost;
            fSkillsAreBoosted = true;
        }
    }

    protected void boostMisc(int bonus) {
        miscBonus = bonus;
    }

    protected void setIsActivatedAbility(boolean isAbil) {this.isActivatedAbility = isAbil;}

    protected void setActionType(String t) {
        actionType = t;
    }

    protected void setSimpleVitalityCost(int vp) { simpleVitalityCost = vp; }

    public void setCanTake10(boolean canTake10) { this.canTake10 = canTake10; }

    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public void initialize() { this.initialized = true; }
}
