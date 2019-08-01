package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Classes.*;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class AlterTest {
    @Test
    public void checkAlter() {
        System.out.println("Testing Alter Feat");
        Player tester1 = new Player(Species.TWILEK, new Scoundrel(), 5, false, "none");
        assert(!tester1.hasFeat("Alter"));
        assert(!tester1.addFeat("Alter", false));
        assert(tester1.addFeat("Force sensitive",false));
        assert(!tester1.addFeat("Alter", false));

        Player tester2 = new Player(Species.CEREAN, new ForceAdept(), 7, false, "none");
        assert(!tester2.hasFeat("Alter"));
        assert(!tester2.addFeat("Alter", false));
        assert(tester2.addFeat("Force sensitive",false));
        assert(tester2.addFeat("Alter", false));

        Player tester3 = new Player(Species.HUMAN, new JediGuardian(), 17, false, "none");
        assert(!tester3.hasFeat("Alter"));
        assert(!tester3.addFeat("Alter", false));
        assert(tester3.addFeat("Force sensitive",false));
        assert(tester3.addFeat("Alter", false));
    }
}
