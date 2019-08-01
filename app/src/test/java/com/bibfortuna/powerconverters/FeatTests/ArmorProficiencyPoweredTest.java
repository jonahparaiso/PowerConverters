package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Classes.Noble;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class ArmorProficiencyPoweredTest {
    @Test
    public void checkPoweredArmor() {
        System.out.println("Testing Armor Proficiency Powered Feat");
        Player tester1 = new Player(Species.QUARREN, new Noble(), 5, false, "none");
        assert(!tester1.hasFeat("Armor Proficiency Powered"));
        assert(!tester1.addFeat("Armor Proficiency Powered", false));
        assert(tester1.addFeat("Armor Proficiency Light", false));
        assert(!tester1.addFeat("Armor Proficiency Powered", false));
        assert(tester1.addFeat("Armor Proficiency Medium", false));
        assert(tester1.addFeat("Armor Proficiency Powered", false));

        Player tester2 = new Player();
        assert(!tester2.hasFeat("Armor Proficiency Powered"));
        assert(!tester2.addFeat("Armor Proficiency Powered", false));
        assert(tester2.addFeat("Armor Proficiency Light", false));
        assert(!tester2.addFeat("Armor Proficiency Powered", false));
        assert(tester2.addFeat("Armor Proficiency Medium", false));
        assert(tester2.addFeat("Armor Proficiency Powered", false));

        Player tester3 = new Player();
        assert(!tester3.hasFeat("Armor Proficiency Powered"));
        assert(!tester3.addFeat("Armor Proficiency Powered", false));
        assert(tester3.addFeat("Armor Proficiency Light", false));
        assert(!tester3.addFeat("Armor Proficiency Powered", false));
        assert(tester3.addFeat("Armor Proficiency Medium", false));
        assert(tester3.addFeat("Armor Proficiency Powered", false));
    }
}
