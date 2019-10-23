package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;
import com.bibfortuna.powerconverters.Skilltype;

import com.bibfortuna.powerconverters.Classes.*;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Species;

public class AwareTest {
    @Test
    public void checkAware() {
        System.out.println("Testing Aware Feat");
        Player tester1 = new Player(Species.BOTHAN, new ForceAdept(), 3, false, "none");
        int baseEnhanceSenses1 = tester1.forceSkills[Skilltype.ENHANCE_SENSES.getIndex()].getMisc();
        int baseSeeForce1 = tester1.forceSkills[Skilltype.SEE_FORCE.getIndex()].getMisc();
        assert(!tester1.hasFeat("AWARE"));
        assert(!tester1.addFeat("Aware", false));
        assert(tester1.addFeat("Force Sensitive", false));
        assert(tester1.hasFeat("Force Sensitive"));
        assert(!tester1.addFeat("Aware", false));
        assert(tester1.addFeat("Sense", false));
        assert(tester1.hasFeat("SENSE"));
        assert(tester1.addFeat("Aware", false));
        int boostedEnhanceSenses1 = tester1.forceSkills[Skilltype.ENHANCE_SENSES.getIndex()].getMisc();
        int boostedSeeForce1 = tester1.forceSkills[Skilltype.SEE_FORCE.getIndex()].getMisc();
        assert(baseEnhanceSenses1 == boostedEnhanceSenses1 - 2);
        assert(baseSeeForce1 == boostedSeeForce1 - 2);

        Player tester2 = new Player(Species.ITHORIAN, new Fringer(), 15, false, "none");
        int baseEnhanceSenses2 = tester2.forceSkills[Skilltype.ENHANCE_SENSES.getIndex()].getMisc();
        int baseSeeForce2 = tester2.forceSkills[Skilltype.SEE_FORCE.getIndex()].getMisc();
        assert(!tester2.hasFeat("AWARE"));
        assert(!tester2.addFeat("Aware", false));
        assert(tester2.addFeat("Force Sensitive", false));
        assert(tester2.hasFeat("Force Sensitive"));
        assert(!tester2.addFeat("Aware", false));
        int boostedEnhanceSenses2 = tester2.forceSkills[Skilltype.ENHANCE_SENSES.getIndex()].getMisc();
        int boostedSeeForce2 = tester2.forceSkills[Skilltype.SEE_FORCE.getIndex()].getMisc();
        assert(baseEnhanceSenses2 != boostedEnhanceSenses2 - 2);
        assert(baseSeeForce2 != boostedSeeForce2 - 2);
    }
}
