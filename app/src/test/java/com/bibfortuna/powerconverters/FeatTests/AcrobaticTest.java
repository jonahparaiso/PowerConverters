package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;
import com.bibfortuna.powerconverters.Player;

public class AcrobaticTest {
    @Test
    public void checkAcrobatic() {
        System.out.println("Testing Acrobatic Feat");
        Player tester = new Player();
        assert(tester.hasFeat("ACROBATIC") == false);
        int totalTumble = tester.skills[tester.getSkillIndex("tumble")].getTotalModifier();
        int miscTumble = tester.skills[tester.getSkillIndex("tumble")].getMisc();
        int totalJump = tester.skills[tester.getSkillIndex("jump")].getTotalModifier();
        int miscJump = tester.skills[tester.getSkillIndex("jump")].getMisc();
        assert(tester.addFeat("Acrobatic", true) == true);
        assert(tester.hasFeat("ACROBATIC") == true);
        tester.initializeFeat("Acrobatic", true);
        int newTotalTumble = tester.skills[tester.getSkillIndex("tumble")].getTotalModifier();
        int newMiscTumble = tester.skills[tester.getSkillIndex("tumble")].getMisc();
        int newTotalJump = tester.skills[tester.getSkillIndex("jump")].getTotalModifier();
        int newMiscJump = tester.skills[tester.getSkillIndex("jump")].getMisc();
        assert(totalTumble == newTotalTumble - 2);
        assert(miscTumble == newMiscTumble - 2);
        assert(totalJump == newTotalJump - 2);
        assert(miscJump == newMiscJump - 2);
        tester.printSkills();
    }
}
