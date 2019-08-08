package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Classes.*;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class AttunedTest {
    @Test
    public void checkAttuned() {
        System.out.println("Testing Attuned Feat");
        Player tester1 = new Player(Species.TWILEK, new Scoundrel(), 5, false, "none");
        assert(!tester1.hasFeat("Attuned"));
        assert(!tester1.addFeat("Attuned", false));
        assert(tester1.addFeat("Force Sensitive",false));
        assert(!tester1.addFeat("Attuned", false));
        assert(!tester1.addFeat("Control",false));
        assert(!tester1.addFeat("Attuned", false));

        Player tester2 = new Player(Species.CEREAN, new ForceAdept(), 7, false, "none");
        assert(!tester2.hasFeat("Attuned"));
        assert(!tester2.addFeat("Attuned", false));
        assert(tester2.addFeat("Force Sensitive",false));
        assert(!tester2.addFeat("Attuned", false));
        assert(tester2.addFeat("Control",false));
        assert(tester2.addFeat("Attuned", false));

        Player tester3 = new Player(Species.HUMAN, new JediGuardian(), 17, false, "none");
        assert(!tester3.hasFeat("Attuned"));
        assert(!tester3.addFeat("Attuned", false));
        assert(tester3.addFeat("Force Sensitive",false));
        assert(!tester3.addFeat("Attuned", false));
        assert(tester3.addFeat("Control",false));
        assert(tester3.addFeat("Attuned", false));
    }
}
