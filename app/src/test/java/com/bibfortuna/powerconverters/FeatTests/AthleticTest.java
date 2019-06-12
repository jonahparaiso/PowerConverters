package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;
import com.bibfortuna.powerconverters.Player;

public class AthleticTest {
    @Test
    public void checkAthletic() {
        System.out.println("Testing Athletic Feat");
        Player tester = new Player();
        assert(tester.hasFeat("Athletic") == false);
        int totalClimb = tester.skills[tester.getSkillIndex("Climb")].getTotalModifier();
        int miscClimb = tester.skills[tester.getSkillIndex("Climb")].getMisc();
        int totalSwim = tester.skills[tester.getSkillIndex("Swim")].getTotalModifier();
        int miscSwim = tester.skills[tester.getSkillIndex("Swim")].getMisc();
        assert(tester.addFeat("Athletic", true) == true);
        assert(tester.hasFeat("Athletic") == true);
        tester.initializeFeat("Athletic", true);
        int newTotalClimb = tester.skills[tester.getSkillIndex("Climb")].getTotalModifier();
        int newMiscClimb = tester.skills[tester.getSkillIndex("Climb")].getMisc();
        int newTotalSwim = tester.skills[tester.getSkillIndex("Swim")].getTotalModifier();
        int newMiscSwim = tester.skills[tester.getSkillIndex("Swim")].getMisc();
        assert(totalClimb == newTotalClimb - 2);
        assert(miscClimb == newMiscClimb - 2);
        assert(totalSwim == newTotalSwim - 2);
        assert(miscSwim == newMiscSwim - 2);
        tester.printSkills();
    }
}
