package com.bibfortuna.powerconverters.FeatTests;
import com.bibfortuna.powerconverters.Classes.ForceAdept;
import com.bibfortuna.powerconverters.Classes.Fringer;
import com.bibfortuna.powerconverters.Classes.Noble;
import com.bibfortuna.powerconverters.Player;
import com.bibfortuna.powerconverters.Skilltype;
import com.bibfortuna.powerconverters.Species;

import org.junit.Test;

public class CautiousTest {
    @Test
    public void checkCautious() {
        System.out.println("Testing Cautious Feat");
        Player tester1 = new Player(Species.GAMORREAN, new Fringer(), 1, false, "none");
        int baseDemolitions1 = tester1.skills[Skilltype.DEMOLITIONS.getIndex()].getMisc();
        int baseDisableDevice1 = tester1.skills[Skilltype.DISABLE_DEVICE.getIndex()].getMisc();
        assert(!tester1.hasFeat("Cautious"));
        assert(tester1.addFeat("Cautious", false));
        int boostedDemolitions1 = tester1.skills[Skilltype.DEMOLITIONS.getIndex()].getMisc();
        int boostedDisableDevice1 = tester1.skills[Skilltype.DISABLE_DEVICE.getIndex()].getMisc();
        assert(baseDemolitions1 == boostedDemolitions1 - 2);
        assert(baseDisableDevice1 == boostedDisableDevice1 - 2);

        Player tester2 = new Player(Species.QUARREN, new Noble(), 1, false, "none");
        int baseDemolitions2 = tester2.skills[Skilltype.DEMOLITIONS.getIndex()].getMisc();
        int baseDisableDevice2 = tester2.skills[Skilltype.DISABLE_DEVICE.getIndex()].getMisc();
        assert(!tester2.hasFeat("Cautious"));
        assert(tester2.addFeat("Cautious", false));
        int boostedDemolitions2 = tester1.skills[Skilltype.DEMOLITIONS.getIndex()].getMisc();
        int boostedDisableDevice2 = tester1.skills[Skilltype.DISABLE_DEVICE.getIndex()].getMisc();
        assert(baseDemolitions2 == boostedDemolitions2 - 2);
        assert(baseDisableDevice2 == boostedDisableDevice2 - 2);
    }
}
