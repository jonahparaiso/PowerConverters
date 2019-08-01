package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Classes.*;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;
public class AmbidexterityTest {
    @Test
    public void ambidexterityTest() {
        System.out.println("Testing Ambidexterity Feat");
        Player tester1 = new Player("Tester1", "Female", Species.KELDOR, new JediGuardian(), 5, 15, 13, 15, 12, 12, 10, false, "list");
        assert(!tester1.hasFeat("Ambidexterity"));
        assert(tester1.addFeat("Ambidexterity", false));

        Player tester2 = new Player("Tester2", "Male", Species.MONCALAMARI, new Scout(), 15, 11, 18, 12, 16, 15, 14, false, "list");
        assert(!tester2.hasFeat("Ambidexterity"));
        assert(tester2.addFeat("Ambidexterity", false));

        Player tester3 = new Player("Tester3", "Female", Species.WOOKIEE, new TechSpecialist(), 1, 7, 9, 12, 18, 17, 12, false, "list");
        assert(!tester3.hasFeat("Ambidexterity"));
        assert(!tester3.addFeat("Ambidexterity", false));
    }
}
