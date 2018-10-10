package com.bibfortuna.powerconverters;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import com.bibfortuna.powerconverters.Classes.*;

public enum Species {
    //Language, Homeworld, Str, Dex, Con, Int, Wis, Cha, Fort, Ref, Will, Speed, Size, Init, Defense

    //TODO 4 extra skill points at lvl 1, +1 skill point at each lvlup, 1 extra feat at lvl 1
    HUMAN(new LinkedList<Language>(Collections.singleton(Language.BASIC)), Planet.CORUSCANT,
            0, 0, 0, 0, 0, 0,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new ForceAdept(), new Fringer(), new JediConsular(), new JediGuardian(), new Noble(), new Scoundrel(), new Scout(), new Soldier(), new TechSpecialist()}),

    //TODO +2 to Gather Information and Spot checks
    BOTHAN(new LinkedList<Language>(Arrays.asList(Language.BOTHESE, Language.BASIC)), Planet.RYLOTH,
            0, 2, -2, 0, 0, 0,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new Fringer(), new Noble(), new Scoundrel(), new Scoundrel(), new Scout(), new Soldier(), new TechSpecialist()}),

    CEREAN(new LinkedList<Language>(Arrays.asList(Language.CEREAN, Language.BASIC)), Planet.CEREA,
            0, -2, 0, 2, 2, 0,
            0, 0, 0,
            10, 0, 2, 0,
            new Classs[]{new ForceAdept(), new JediConsular(), new JediConsular(), new JediConsular(), new JediGuardian(), new JediGuardian(), new Noble(), new TechSpecialist()}),

    //TODO Starts with the Spacer Feat as a bonus feat at lvl 1
    DUROS(new LinkedList<Language>(Arrays.asList(Language.DURESE, Language.BASIC)), Planet.DURO,
            -2, 2, -2, 2, 0, 0,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new Fringer(), new Scoundrel(), new Scoundrel(), new Scoundrel(), new Scout(), new TechSpecialist(), new TechSpecialist()}),

    //TODO Bonus feats: Weapon Group Proficiency(primitive, simple), Alertness
    EWOK(new LinkedList<Language>(Collections.singleton(Language.EWOKESE)), Planet.ENDOR,
            -2, 2, 0, 0, 0, 0,
            0, 0, 0,
            6, 1, 0, 0,
            new Classs[]{new Scoundrel(), new Scoundrel(), new Scout(), new Scout(), new Soldier(), new Soldier()}),

    //TODO Bonus feats: Weapon Group Proficiency(primitive, simple), Power Attack
    GAMORREAN(new LinkedList<Language>(Collections.singleton(Language.GAMORRESE)), Planet.GAMORR,
            2, -2, 0, -2, 0, 0,
            2, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new Fringer(), new Fringer(), new Fringer(), new Soldier(), new Soldier(), new Soldier(), new Soldier()}),

    //TODO +4 Swim checks, holds breath for 25x Con before drowning, +2 Listen, Low-Light Vision
    GUNGAN(new LinkedList<Language>(Arrays.asList(Language.GUNGAN, Language.BASIC)), Planet.NABOO,
            0, 0, 2, 0, -2, 0,
            0, 1, 0,
            10, 0, 0, 0,
            new Classs[]{new ForceAdept(), new Fringer(), new Scout(), new Scout()}),

    //TODO +4 Survival checks, +2 Knowledge (Wilderness lore)
    ITHORIAN(new LinkedList<Language>(Arrays.asList(Language.ITHORESE, Language.BASIC)), Planet.ITHOR,
            0, -2, 0, 0, 2, 2,
            0, 0, 1,
            10, 0, 0, 0,
            new Classs[]{new ForceAdept(), new JediConsular(), new JediConsular(), new JediConsular(), new Noble(), new Noble(), new Scout()}),

    //TODO Low-Light Vision, Gas Breather-Suffocates without their mask and can't see without their goggles
    KELDOR(new LinkedList<Language>(Arrays.asList(Language.KEL_DOR, Language.BASIC)), Planet.DORIN,
            0, 2, -2, 0, 2, 0,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new ForceAdept(), new JediConsular(), new JediConsular(), new JediGuardian(), new Noble(), new Noble(), new Scoundrel(), new Scout()}),

    //TODO Low-Light Vision, Breathe underwater, +4 Craft(choose), +1/-1 Will save in wet/dry conditions
    MONCALAMARI(new LinkedList<Language>(Arrays.asList(Language.MON_CALAMARIAN, Language.QUARRENESE, Language.BASIC)), Planet.MON_CALAMARI,
            0, 0, -2, 2, 0, 0,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new Fringer(), new JediConsular(), new Noble(), new Noble(), new Scout(), new TechSpecialist(), new TechSpecialist(), new TechSpecialist()}),

    //TODO Breathe Underwater
    QUARREN(new LinkedList<Language>(Arrays.asList(Language.QUARRENESE, Language.MON_CALAMARIAN, Language.BASIC)), Planet.MON_CALAMARI,
            0, 0, 2, 0, -2, -2,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new Fringer(), new Fringer(), new Noble(), new Noble(), new Scoundrel(), new Soldier(), new Soldier(), new Soldier()}),

    //TODO Bonus Feat: Track, +2 bonus to Listen, Search, and Spot checks
    RODIAN(new LinkedList<Language>(Arrays.asList(Language.RODESE, Language.HUTTESE, Language.BASIC)), Planet.RODIA,
            0, 2, 0, 0, -2, -2,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new Scout(), new Scout(), new Scout(), new Scout(), new Scoundrel(), new Scoundrel(), new Scoundrel(), new Noble()}),

    //TODO Darkvision, +2 Climb and Listen checks
    SULLUSTAN(new LinkedList<Language>(Arrays.asList(Language.SULLUSTESE, Language.BASIC)), Planet.SULLUST,
            0, 2, -2, 0, 0, 0,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new ForceAdept(), new Fringer(), new Noble(), new Scoundrel(), new Scoundrel(), new Scoundrel(), new Scout(), new Soldier(), new TechSpecialist()}),

    //TODO Darkvision
    TRANDOSHAN(new LinkedList<Language>(Arrays.asList(Language.DOSH, Language.BASIC)), Planet.TRANDOSHA,
            2, -2, 0, 0, 0, 0,
            0, 0, 0,
            10, 0, 0, 1,
            new Classs[]{new Fringer(), new JediGuardian(), new Scout(), new Scout(), new Scoundrel(), new Soldier(), new Soldier()}),

    //TODO Low-Light Vision
    TWILEK(new LinkedList<Language>(Arrays.asList(Language.RYL, Language.LEKKU, Language.BASIC)), Planet.RYLOTH,
            0, 0, 0, 0, -2, 2,
            1, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new Noble(), new Noble(), new Noble(), new Scoundrel(), new Scoundrel(), new Scoundrel(), new Scoundrel(), new ForceAdept(), new ForceAdept(), new JediGuardian()}),

    //TODO Wookie Rage, +4 Intimidate, +2 Climb, Extraordinary Recuperation (Heals twice as fast)
    WOOKIEE(new LinkedList<Language>(Arrays.asList(Language.SHYRIIWOOK, Language.BASIC)), Planet.KASHYYYK,
            4, -2, 0, 0, -2, -2,
            0, 0, 0,
            10, 0, 0, 0,
            new Classs[]{new ForceAdept(), new Fringer(), new Fringer(), new JediGuardian(), new JediGuardian(), new JediGuardian(), new Scout(), new Soldier(), new Soldier(), new Soldier()}),

    ZABRAK(new LinkedList<Language>(Arrays.asList(Language.ZABRAK, Language.BASIC)), Planet.IRIDONIA,
            0, 0, 0, 0, 0, 0,
            2, 0, 2,
            10, 0, 0, 0,
            new Classs[]{new ForceAdept(), new JediConsular(), new JediGuardian(), new TechSpecialist()});

    //Language
    private LinkedList<Language> lang;
    private Planet homeworld;

    //Attribute modifiers
    private int strMod;
    private int dexMod;
    private int conMod;
    private int intMod;
    private int wisMod;
    private int chaMod;

    //Saving throw modifiers
    private int fortMod;
    private int reflexMod;
    private int willMod;

    //Other
    private int speed;
    private int size;
    private int init;
    private int defense;

    private Classs[] favoriteClasses;

    //TODO
    /*
    Need to create a way to add modifiers to specific skills, or grant bonus feats, etc
     */

    //Language, Homeworld, Str, Dex, Con, Int, Wis, Cha, Fort, Ref, Will, Speed, Size, Init, Def
    Species(LinkedList<Language> l, Planet p,
            int s, int d, int c, int i, int w, int ch,
            int fort, int ref, int will,
            int sp, int sz, int it, int def,
            Classs[] favClasses) {
        lang = l;
        homeworld = p;
        strMod = s;
        dexMod = d;
        conMod = c;
        intMod = i;
        wisMod = w;
        chaMod = ch;
        fortMod = fort;
        reflexMod = ref;
        willMod = will;
        speed = sp;
        size = sz;
        init = it;
        defense = def;
        favoriteClasses = favClasses;
    }

    //Getter Methods
    LinkedList<Language> getLang() {
        return lang;
    }

    Planet getHomeworld() {
        return homeworld;
    }

    int getStrMod() {
        return strMod;
    }

    int getDexMod() {
        return dexMod;
    }

    int getConMod() {
        return conMod;
    }

    int getIntMod() {
        return intMod;
    }

    int getWisMod() {
        return wisMod;
    }

    int getChaMod() {
        return chaMod;
    }

    int getFortMod() {
        return fortMod;
    }

    int getReflexMod() {
        return reflexMod;
    }

    int getWillMod() {
        return willMod;
    }

    int getSpeed() {
        return speed;
    }

    int getSize() {
        return size;
    }

    int getInit() {
        return init;
    }

    int getDef() {
        return defense;
    }

    Classs[] getFavoriteClasses() { return favoriteClasses; }
}
