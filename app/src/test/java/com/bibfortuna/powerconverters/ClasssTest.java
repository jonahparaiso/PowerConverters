package com.bibfortuna.powerconverters;

import com.bibfortuna.powerconverters.Classes.Classs;
import com.bibfortuna.powerconverters.Classes.ForceAdept;
import com.bibfortuna.powerconverters.Classes.Fringer;
import com.bibfortuna.powerconverters.Classes.JediConsular;
import com.bibfortuna.powerconverters.Classes.JediGuardian;
import com.bibfortuna.powerconverters.Classes.Noble;
import com.bibfortuna.powerconverters.Classes.Scoundrel;
import com.bibfortuna.powerconverters.Classes.Scout;
import com.bibfortuna.powerconverters.Classes.Soldier;
import com.bibfortuna.powerconverters.Classes.TechSpecialist;

import java.util.ArrayList;

public class ClasssTest {
    private Classs classs;
    String name;

    public ClasssTest(Classs c) {
        this.classs = c;
        this.name = c.getName();
    }

    /**Ensures all data was correctly inputted.
     *
     * @return true iff all the array lengths are exactly 20.
     */
    private void testArrayLengths() {
        boolean bab = false;
        boolean fort = false;
        boolean ref = false;
        boolean will = false;
        boolean def = false;
        boolean rep = false;
        if (classs.getBAB().length == 20) bab = true;
        else System.out.printf("Base Attack Bonuses were not entered properly for %s%n", name);
        if (classs.getFortSave().length == 20) fort = true;
        else System.out.printf("Fortitude saves were not entered properly for %s%n", name);
        if (classs.getRefSave().length == 20) ref = true;
        else System.out.printf("Reflex saves were not entered properly for %s%n", name);
        if (classs.getWillSave().length == 20) will = true;
        else System.out.printf("Will saves were not entered properly for %s%n", name);
        if (classs.getDefense().length == 20) def = true;
        else System.out.printf("Defense bonuses were not entered properly for %s%n", name);
        if (classs.getReputation().length == 20) rep = true;
        else System.out.printf("Reputation datums were not entered properly for %s%n", name);

        System.out.printf("Result of TestArrayLengths for %s: %b%n%n", name, (bab && fort && ref && will && def && rep));
    }

    /**
     * Prints out the list of Class skills for the Class object calling this method.
     */
    private void printSkills() {
        ArrayList<Integer> skillIndeces = classs.getClassSkills();
        Skill s = new Skill();
        int j;
        Skill[] skills = s.populateSkills(classs);
        Skill[] fSkills = s.populateForceSkills();

        System.out.printf("Testing %s's class skills%n", name);
        //System.out.println(Arrays.toString(skillIndeces));

        //Actually setting the class skills
        for (int i = 0; i < skillIndeces.size(); i++) {
            j = skillIndeces.get(i).intValue();
            skills[j].setClassSkill(true);
        }

        System.out.printf("Class skills for %s are as such:%n", name);
        for (Skill temp : skills) {
            System.out.printf("%s : %b%n",temp.getType().getName(), temp.isClassSkill());
        }
        System.out.printf("%nForce Class Skills for %s are as such:%n", name);
        for (Skill temp : fSkills) {
            System.out.printf("%s : %b%n",temp.getType().getName(), temp.isClassSkill());
        }
        System.out.printf("Testing printSkills for %s is complete%n%n", name);
    }

    /**Prints a full class base numbers chart for Attack Bonus, Saving Throws, Defense, Reputation
     * TODO Need to implement feats and abilities on the chart.
     * Possibly by having Feat and Ability objects with a String "name" field?
     */
    private void printChart() {
        System.out.printf("Printing data table for %s%n", name);
        int[] bab = classs.getBAB();
        int[] fort = classs.getFortSave();
        int[] ref = classs.getRefSave();
        int[] will = classs.getWillSave();
        int[] def = classs.getDefense();
        int[] rep = classs.getReputation();

        //xxxx-xxxx-xxxx-xxxx-xxxx-xxxx
        System.out.println("Lvl--BAB--Fort-Ref--Will-Def--Rep--Feat-------------Abil--------------");
        for(int i = 0; i < bab.length; i++) {
            System.out.printf("%d", i+1);
            if((i+1)>9) System.out.print("   ");
            else System.out.print("    ");
            System.out.printf("%d", bab[i]);
            if(bab[i]>9) System.out.print("   ");
            else System.out.print("    ");
            System.out.printf("%d", fort[i]);
            if(fort[i]>9) System.out.print("   ");
            else System.out.print("    ");
            System.out.printf("%d", ref[i]);
            if(ref[i]>9) System.out.print("   ");
            else System.out.print("    ");
            System.out.printf("%d", will[i]);
            if(will[i]>9) System.out.print("   ");
            else System.out.print("    ");
            System.out.printf("%d", def[i]);
            if(def[i]>9) System.out.print("   ");
            else System.out.print("    ");
            System.out.printf("%d%n", rep[i]);
        }
        System.out.println("----------------------------------------------------------------------");
    }


    public static void main(String[] args) {
        ClasssTest fringer = new ClasssTest(new Fringer());
        ClasssTest noble = new ClasssTest(new Noble());
        ClasssTest scoundrel = new ClasssTest(new Scoundrel());
        ClasssTest scout = new ClasssTest(new Scout());
        ClasssTest soldier = new ClasssTest(new Soldier());
        ClasssTest techie = new ClasssTest(new TechSpecialist());
        ClasssTest adept = new ClasssTest(new ForceAdept());
        ClasssTest consular = new ClasssTest(new JediConsular());
        ClasssTest guardian = new ClasssTest(new JediGuardian());

        /** Enable these booleans to true if you want test the relative methods */
        boolean testArrayLengths = true;
        boolean testPrintSkills = true;
        boolean testPrintCharts = true;

        if (testArrayLengths) {
            fringer.testArrayLengths();
            noble.testArrayLengths();
            scoundrel.testArrayLengths();
            scout.testArrayLengths();
            soldier.testArrayLengths();
            techie.testArrayLengths();
            adept.testArrayLengths();
            consular.testArrayLengths();
            guardian.testArrayLengths();
        }

        if (testPrintSkills) {
            fringer.printSkills();
            noble.printSkills();
            scoundrel.printSkills();
            scout.printSkills();
            soldier.printSkills();
            techie.printSkills();
            adept.printSkills();
            consular.printSkills();
            guardian.printSkills();
        }

        if (testPrintCharts) {
            fringer.printChart();
            noble.printChart();
            scoundrel.printChart();
            scout.printChart();
            soldier.printChart();
            techie.printChart();
            adept.printChart();
            consular.printChart();
            guardian.printChart();
        }
    }
}
