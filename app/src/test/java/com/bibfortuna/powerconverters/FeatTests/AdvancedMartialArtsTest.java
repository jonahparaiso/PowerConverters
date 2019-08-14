package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Classes.*;
import com.bibfortuna.powerconverters.Feats.AdvancedMartialArts;
import com.bibfortuna.powerconverters.Feats.Feat;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class AdvancedMartialArtsTest {
    @Test
    public void checkAdvancedMartialArts() {
        System.out.println("Testing Advanced Martial Arts Feat");
        Player tester1 = new Player(Species.WOOKIEE, new JediGuardian(), 5, false, "none");
        assert(!tester1.hasFeat("Advanced Martial Arts"));
        assert(!tester1.addFeat("Advanced Martial Arts", false));
        assert(tester1.addFeat("Martial Arts", false));
        assert(!tester1.addFeat("Advanced Martial Arts", false));
        assert(tester1.addFeat("Improved Martial Arts", false));
        assert(!tester1.addFeat("Advanced Martial Arts", false));
        //Should fail because tester doesn't have a high enough BAB

        Player tester2 = new Player(Species.EWOK, new Soldier(), 7, false, "none");
        assert(!tester2.hasFeat("Advanced Martial Arts"));
        assert(!tester2.addFeat("Advanced Martial Arts", false));
        assert(tester2.addFeat("Martial Arts", false));
        assert(!tester2.addFeat("Advanced Martial Arts", false));
        assert(tester2.addFeat("Improved Martial Arts", false));
        assert(!tester2.addFeat("Advanced Martial Arts", false));
        //Should fail because tester2 doesn't have a high enough BAB

        Player tester3 = new Player(Species.TRANDOSHAN, new Scoundrel(), 11, false, "none");
        assert(!tester3.hasFeat("Advanced Martial Arts"));
        assert(!tester3.addFeat("Advanced Martial Arts", false));
        assert(tester3.addFeat("Martial Arts", false));
        assert(!tester3.addFeat("Advanced Martial Arts", false));
        assert(tester3.addFeat("Improved Martial Arts", false));
        assert(tester3.addFeat("Advanced Martial Arts", false));

        Feat featTest = new AdvancedMartialArts();
        assert(!featTest.isActivatedAbility());
        assert(featTest.getPrereqs().size() == 3);
        assert(!featTest.isActivatedAbility());
        assert(featTest.getType() == "");
        assert(featTest.getSimpleVitalityCost() == 0);
        assert(!featTest.getCanTake10());
    }
}
