package com.bibfortuna.powerconverters;

import com.bibfortuna.powerconverters.Classes.Fringer;
import com.bibfortuna.powerconverters.Classes.JediConsular;
import com.bibfortuna.powerconverters.Classes.JediGuardian;
import com.bibfortuna.powerconverters.Classes.Noble;
import com.bibfortuna.powerconverters.Classes.Scoundrel;
import com.bibfortuna.powerconverters.Classes.Scout;
import com.bibfortuna.powerconverters.Classes.Soldier;
import com.bibfortuna.powerconverters.Classes.TechSpecialist;
import com.bibfortuna.powerconverters.Classes.Thug;

import org.junit.Test;

public class PlayerTest {
    @Test
    public void printCharacterSheet() {
//        Player tester = new Player("Tigo Ripplez", "Male", Species.RODIAN, new JediConsular(), 20, 7, 12, 7, 16, 17, 18, true);
//        tester.levelUp(new Scoundrel(), 7, true);
//        tester.levelUp(new Noble(), 6, true);

        Player jonah = new Player(new Scoundrel(), 13, new JediGuardian(), 7, true);
        jonah.printSkills();
        jonah.printAttributes();
        jonah.printSavingThrows();
        jonah.printMisc();
        jonah.printHealth();
        jonah.printCombat();

//        Player yolo = new Player();
//        yolo.printSkills();
//        yolo.printAttributes();
//        yolo.printSavingThrows();
//        yolo.printMisc();
//        yolo.printHealth();
//        yolo.printCombat();
//        yolo.printFeats();

        //Player multi = new Player(new JediConsular(), 12, new Scoundrel(), 8, true);
    }
}
