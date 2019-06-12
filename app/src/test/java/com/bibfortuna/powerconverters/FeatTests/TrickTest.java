package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;
import com.bibfortuna.powerconverters.Player;

public class TrickTest {
    @Test
    public void checkTrick() {
        System.out.println("Testing Trick Feat");
        Player tester = new Player();
        assert(tester.hasFeat("Trick") == false);
        int totalGamble = tester.skills[tester.getSkillIndex("Gamble")].getTotalModifier();
        int miscGamble = tester.skills[tester.getSkillIndex("Gamble")].getMisc();
        int totalBluff = tester.skills[tester.getSkillIndex("Bluff")].getTotalModifier();
        int miscBluff = tester.skills[tester.getSkillIndex("Bluff")].getMisc();
        assert(tester.addFeat("Trick", true) == true);
        assert(tester.hasFeat("Trick") == true);
        tester.initializeFeat("Trick", true);
        int newTotalGamble = tester.skills[tester.getSkillIndex("Gamble")].getTotalModifier();
        int newMiscGamble = tester.skills[tester.getSkillIndex("Gamble")].getMisc();
        int newTotalBluff = tester.skills[tester.getSkillIndex("Bluff")].getTotalModifier();
        int newMiscBluff = tester.skills[tester.getSkillIndex("Bluff")].getMisc();
        assert(totalGamble == newTotalGamble - 2);
        assert(miscGamble == newMiscGamble - 2);
        assert(totalBluff == newTotalBluff - 2);
        assert(miscBluff == newMiscBluff - 2);
        tester.printSkills();
    }
}
