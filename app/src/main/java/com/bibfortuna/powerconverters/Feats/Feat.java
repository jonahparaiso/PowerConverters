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

    private int[] skillsBoosted;
    private int[] fSkillsBoosted;
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
        this.skillsAreBoosted = false;
        this.fSkillsAreBoosted = false;
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

    //////////////////////////////////////////SETTERS///////////////////////////////////////////////

    protected void boostSkill(int index, int boost, boolean isForce) {
        if (!isForce) {
            skillsBoosted = new int[39];
            skillsBoosted[index] = boost;
            skillsAreBoosted = true;
        }
        else {
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
