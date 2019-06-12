package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;
import com.bibfortuna.powerconverters.Player;

public class AlertnessTest {
    @Test
    public void checkAlertness() {
        System.out.println("Testing Alertness Feat");
        Player tester = new Player();
        assert(tester.hasFeat("ALERTNESS") == false);
        int totalListen = tester.skills[tester.getSkillIndex("Listen")].getTotalModifier();
        int miscListen = tester.skills[tester.getSkillIndex("Listen")].getMisc();
        int totalSpot = tester.skills[tester.getSkillIndex("Spot")].getTotalModifier();
        int miscSpot = tester.skills[tester.getSkillIndex("Spot")].getMisc();
        assert(tester.addFeat("Alertness", true) == true);
        assert(tester.hasFeat("AlerTNEsS") == true);
        tester.initializeFeat("alertness", true);
        int newTotalListen = tester.skills[tester.getSkillIndex("Listen")].getTotalModifier();
        int newMiscListen = tester.skills[tester.getSkillIndex("Listen")].getMisc();
        int newTotalSpot = tester.skills[tester.getSkillIndex("Spot")].getTotalModifier();
        int newMiscSpot = tester.skills[tester.getSkillIndex("Spot")].getMisc();
        assert(totalListen == newTotalListen - 2);
        assert(miscListen == newMiscListen - 2);
        assert(totalSpot == newTotalSpot - 2);
        assert(miscSpot == newMiscSpot - 2);
        tester.printSkills();
    }
}
