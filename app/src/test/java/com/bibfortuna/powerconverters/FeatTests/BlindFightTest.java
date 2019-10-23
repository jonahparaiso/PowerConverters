package com.bibfortuna.powerconverters.FeatTests;
import com.bibfortuna.powerconverters.Classes.ForceAdept;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

import org.junit.Test;

public class BlindFightTest {
    @Test
    public void checkBlindFight() {
        System.out.println("Testing Blind Fight Feat");
        Player tester1 = new Player(Species.BOTHAN, new ForceAdept(), 1, false, "none");
        assert(!tester1.hasFeat("blind fight"));
        assert(tester1.addFeat("Blind Fight", false));

    }
}
