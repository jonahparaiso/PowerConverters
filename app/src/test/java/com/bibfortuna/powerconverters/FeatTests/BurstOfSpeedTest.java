package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;

import com.bibfortuna.powerconverters.Feats.BurstOfSpeed;
import com.bibfortuna.powerconverters.Feats.Feat;

import com.bibfortuna.powerconverters.Classes.*;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class BurstOfSpeedTest {
    @Test
    public void checkBurstOfSpeed() {
        System.out.println("Testing Burst of Speed");
        Player tester1 = new Player(Species.ZABRAK, new JediGuardian(), 3, false, "none");
        assert(!tester1.hasFeat("burst of speed"));
        assert(!tester1.addFeat("burst of speed"));
        assert(tester1.addFeat("force sensitive"));
        assert(!tester1.addFeat("burst of speed"));
        assert(tester1.addFeat("control"));
        assert(tester1.addFeat("burst of speed"));

        Player tester2 = new Player(Species.ITHORIAN, new JediConsular(), 2, false, "none");
        assert(!tester2.hasFeat("burst of speed"));
        assert(!tester2.addFeat("burst of speed"));
        assert(tester2.addFeat("force sensitive"));
        assert(!tester2.addFeat("burst of speed"));
        assert(tester2.addFeat("control"));
        assert(!tester2.addFeat("burst of speed"));

        Player tester3 = new Player(Species.QUARREN, new Noble(), 13, false, "none");
        assert(!tester3.hasFeat("burst of speed"));
        assert(!tester3.addFeat("burst of speed"));
        assert(tester3.addFeat("force sensitive"));
        assert(!tester3.addFeat("burst of speed"));
        assert(!tester3.addFeat("control"));
        assert(!tester3.addFeat("burst of speed"));

        Feat bursty = new BurstOfSpeed();
        assert(bursty.isActivatedAbility());
        assert(bursty.getPrereqs().size() == 3);
        assert(bursty.isActivatedAbility());
        assert(bursty.getType() == "Free action");
        assert(bursty.getSimpleVitalityCost() == 5);
    }
}
