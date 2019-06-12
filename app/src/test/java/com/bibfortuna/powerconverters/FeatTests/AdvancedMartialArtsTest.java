package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Classes.JediGuardian;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class AdvancedMartialArtsTest {
    @Test
    public void checkAdvancedMartialArts() {
        System.out.println("Testing Advanced Martial Arts Feat");
        Player tester = new Player(Species.WOOKIEE, new JediGuardian(), 5, true, "none");
        assert(tester.hasFeat("Advanced Martial Arts") == false);
        assert(tester.addFeat("Advanced Martial Arts", true) == false);
        assert(tester.addFeat("Advanced Martial Arts", true) == false);
        assert(tester.hasFeat("Advanced Martial Arts") == true);
        tester.initializeFeat("Advanced Martial Arts", true);
    }
}
