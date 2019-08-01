package com.bibfortuna.powerconverters;

import com.bibfortuna.powerconverters.Classes.*;

import org.junit.Test;

public class PlayerTest {
    @Test
    public void printCharacterSheet() {
        System.out.println("Testing multiclass constructor");
//        Player tester = new Player("Tigo Ripplez", "Male", Species.RODIAN, new JediConsular(), 10, 7, 12, 7, 16, 17, 18, true);
//        tester.levelUp(new Scoundrel(), 7, true);
//        tester.levelUp(new Noble(), 6, true);

//        Player jonah = new Player(new Scoundrel(), 13, new JediGuardian(), 7, true, "feats");
//        jonah.printSkills();
//        jonah.printAttributes();
//        jonah.printSavingThrows();
//        jonah.printMisc();
//        jonah.printHealth();
//        jonah.printCombat();

//        Player yolo = new Player();
//        yolo.printSkills();
//        yolo.printAttributes();
//        yolo.printSavingThrows();
//        yolo.printMisc();
//        yolo.printHealth();
//        yolo.printCombat();
//        yolo.printFeats();

//        Player multi = new Player(new TechSpecialist(), 10, new Scoundrel(), 2, true, "list");
    }

    @Test
    public void printConstructor3() {
//        System.out.println("Testing printConstructor3");
        Player gunthar = new Player(Species.CEREAN, new Scout(), 4, true, "list");
        gunthar.printSkills();
        gunthar.printAttributes();
        gunthar.printCombat();
        gunthar.printHealth();
        gunthar.printMisc();
        gunthar.printSavingThrows();
    }
}
