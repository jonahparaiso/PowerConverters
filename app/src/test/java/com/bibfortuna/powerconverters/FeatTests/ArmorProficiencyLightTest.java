package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Classes.Noble;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class ArmorProficiencyLightTest {
    @Test
    public void checkLightArmor() {
        System.out.println("Testing Armor Proficiency Light Feat");
        Player tester1 = new Player(Species.QUARREN, new Noble(), 5, false, "none");
        assert(!tester1.hasFeat("Armor Proficiency Light"));
        assert(tester1.addFeat("Armor Proficiency Light", false));

        Player tester2 = new Player();
        assert(!tester2.hasFeat("Armor Proficiency Light"));
        assert(tester2.addFeat("Armor Proficiency Light", false));

        Player tester3 = new Player();
        assert(!tester3.hasFeat("Armor Proficiency Light"));
        assert(tester3.addFeat("Armor Proficiency Light", false));
    }
}
