package com.bibfortuna.powerconverters.FeatTests;
import org.junit.Test;
import com.bibfortuna.powerconverters.Player;

public class AnimalAffinityTest {
    @Test
    public void checkAnimalAffinity() {
        System.out.println("Testing Animal Affinity Feat");
        Player tester = new Player();
        assert(tester.hasFeat("Animal Affinity") == false);
        int totalRide = tester.skills[tester.getSkillIndex("Ride")].getTotalModifier();
        int miscRide = tester.skills[tester.getSkillIndex("Ride")].getMisc();
        int totalHandleAnimal = tester.skills[tester.getSkillIndex("Handle Animal")].getTotalModifier();
        int miscHandleAnimal = tester.skills[tester.getSkillIndex("Handle Animal")].getMisc();
        assert(tester.addFeat("Animal Affinity", true) == true);
        assert(tester.hasFeat("Animal Affinity") == true);
        tester.initializeFeat("Animal Affinity", true);
        int newTotalRide = tester.skills[tester.getSkillIndex("Ride")].getTotalModifier();
        int newMiscRide = tester.skills[tester.getSkillIndex("Ride")].getMisc();
        int newTotalHandleAnimal = tester.skills[tester.getSkillIndex("Handle Animal")].getTotalModifier();
        int newMiscHandleAnimal = tester.skills[tester.getSkillIndex("Handle Animal")].getMisc();
        assert(totalRide == newTotalRide - 2);
        assert(miscRide == newMiscRide - 2);
        assert(totalHandleAnimal == newTotalHandleAnimal - 2);
        assert(miscHandleAnimal == newMiscHandleAnimal - 2);
        tester.printSkills();
    }
}
