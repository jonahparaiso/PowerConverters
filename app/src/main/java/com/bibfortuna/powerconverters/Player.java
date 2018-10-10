package com.bibfortuna.powerconverters;

import com.bibfortuna.powerconverters.Feats.*;
import com.bibfortuna.powerconverters.Classes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;

public class Player {
    protected Dice die;//NOT THE VITALITY DIE, this is used to randomly determine data
    protected final String name;
    protected final Species race;
    protected final String gender;
    protected int level;
    protected int forceLevel;
    protected int exp;
    protected int credits;
    protected Planet homeworld;

    protected HashMap<String, Classs> classes;
    protected int classPenalty;
    protected String firstClass;

    /**
     * For the purpose of leveling up and multiclassing, there needs to be a reference to the newest
     * class leveling up.
     */

    /**
     * Attributes
     * Main: The ^permanent^ (not final only because it could be incremented every four levels) stat.
     * <p>
     * Main Modifier: The bonus used for skill/combat/any checks. Is the result of (Main - 10)/2.
     * <p>
     * Temp Score: Represents an additional or reductive number to the Main stat. For example, if a
     * hero, Han Cholo, naturally has STR 13, but has only just awoken from a long Carbonite freeze,
     * he would have -3 (or something like that, tbd by the DM) in the Temp Score, effectively making
     * his in game total STR be 10. Thusly, any feats or etc that have prerequisites of STR 11, 12,
     * or 13 would temporarily be unusable.
     * <p>
     * Temp Modifier: Represents an additional or reductive number to the Main Modifier. For example,
     * Han Cholo was the target of his good buddy Duke Flyswatter's Focus Mind and received
     * an additional +5 to his DEX, then that number would go here. If Cholo's natural DEX is 16,
     * then his natural DEX modifier would be 3. Under the bonus, his temporary TOTAL mod would be
     * 8=5+3. To be clear, this does NOT make his Temp DEX score go up by 10 from 16 -> 26. TODO
     */
    protected int STR;
    protected int sMod;
    protected int strTempScore;
    protected int strTempMod;
    protected boolean isStrBuffed;

    protected int DEX;
    protected int dMod;
    protected int dexTempMod;
    protected int dexTempScore;
    protected boolean isDexBuffed;

    protected int CON;
    protected int cMod;
    protected int conTempScore;
    protected int conTempMod;
    protected boolean isConBuffed;

    protected int INT;
    protected int iMod;
    protected int intTempScore;
    protected int intTempMod;
    protected boolean isIntBuffed;

    protected int WIS;
    protected int wMod;
    protected int wisTempScore;
    protected int wisTempMod;
    protected boolean isWisBuffed;

    protected int CHA;
    protected int chMod;
    protected int chaTempScore;
    protected int chaTempMod;
    protected boolean isChaBuffed;


    /**
     * Saving Throws
     */
    protected int fortitude;// = baseFortitude + cMod + fortitudeMiscMod;
    protected int baseFortitude;
    protected int fortitudeMiscMod;

    protected int reflex;// = baseReflex + dmod + reflexMiscMod;
    protected int baseReflex;
    protected int reflexMiscMod;

    protected int will;// = baseWill + wMod + willMiscMod;
    protected int baseWill;
    protected int willMiscMod;

    /**
     * Combat
     */
    protected int bab;
    protected int sizeMod;

    protected int melee;// = bab + sMod + sizeMod + meleeMiscMod;
    protected int meleeMiscMod;

    protected int ranged;// = bab + dMod + sizeMod + meleeMiscMod;
    protected int rangedMiscMod;

    protected int defense;// = 10 + classDefBonus + dMod + dexTempMod + sizeMod + defenseMiscBonus + dodgeBonus
    protected int dodgeBonus;
    protected int classDefBonus;
    protected int defenseMiscBonus;
    //protected int defenseArmorCheck;

    protected int speed;

    protected int initiative;// = dMod + initiativeMiscBonus
    protected int initiativeMiscBonus;

    /**
     * Health
     */
    protected int vitalityRolls;
    protected int maxVP;
    protected int currentVP;
    protected int maxWP;
    protected int currentWP;
    protected int vitalityDie;

    /**
     * Miscellaneous data fields
     */
    protected int reputation;
    protected int forcePoints;
    protected int darkSidePoints;

    //Arrays of skills, 39 regular skills and 20 force skills
    protected Skill[] skills;
    protected Skill[] forceSkills;
    protected int skillPointsToSpend;
    protected int totalRanks;

    protected LinkedList<Language> languages;

    protected LinkedList<Ability> abilities;

    //TODO Need to add the rest of the member variables and figure out how to do feats, items, etc.
    public HashMap<String,Feat> feats;
    protected HashMap<String,Feat> featLibrary;


    /**TODO Weapons and Armor*/
    //protected LinkedList<Item> items;
    //protected LinkedList<Weapon> weapons;

    public Player() {
        this.die = new Dice();
        int lvl = die.roll(20);
        boolean printing = true;
        switch (die.roll(2)) {
            case 1:
                gender = "male";
                break;
            default:
                gender = "female";
                break;
        }

        this.name = randomName(gender, printing);
        this.race = randomRace();
        Classs c = randomClasss(race, printing);
        this.classes = new HashMap<>();
        classes.put(c.name, c);
        this.firstClass = c.name;
        this.level = 0;
        this.exp = 0;

        randomNewAttributes(c, printing);
        initializeVitalityWoundPoints(c, printing);
        applySpeciesBonus(race, printing);
        refreshMods();
        applyClasss(c, printing);

        levelUp(c, lvl, printing);

        //testing all feats
        for (String key : featLibrary.keySet())
            addFeat(key, printing);
        initializeFeats(printing);


        if (printing) {
            printSkills();
            printAttributes();
            printSavingThrows();
            printMisc();
            printHealth();
            printCombat();
            printFeats();
        }
    }


    /**
     * Player assuming the player knows their starting attribute scores, class, species, etc
     *
     * @param n   = name
     * @param r   = species
     * @param c   = class
     * @param lvl = starting level
     * @param str = starting Strength
     * @param dex = starting Dexterity
     * @param con = starting Constitution
     * @param iq  = starting Intelligence
     * @param wis = starting Wisdom
     * @param cha = starting Charisma
     */
    public Player(String n, String sex, Species r, Classs c, int lvl, int str, int dex, int con, int iq, int wis, int cha, boolean printing) {
        if (lvl > 20) {
            System.out.printf("Max level for %s is 20", n);
            lvl = 20;
        }
        if (lvl <= 0) {
            System.out.printf("Min level for %s is 1", n);
            lvl = 1;
        }
        this.die = new Dice();
        this.name = n;
        if (sex.contains("m"))
            this.gender = "Male";
        else
            this.gender = "Female";
        this.race = r;
        this.classes = new HashMap<>();
        classes.put(c.name, c);
        this.firstClass = c.name;

        this.level = 0;
        this.exp = 0;

        this.STR = str;
        this.DEX = dex;
        this.CON = con;
        this.INT = iq;
        this.WIS = wis;
        this.CHA = cha;

        initializeForcePoints(c, lvl);
        applySpeciesBonus(r, printing);
        refreshMods();
        applyClasss(c, printing);
        levelUp(c, lvl, printing);
        //testing all feats
        for (String key : featLibrary.keySet())
            addFeat(key, printing);
        feats.put("Force Flight", featLibrary.get("Force Flight"));
        initializeFeats(printing);
    }

    /**
     * Randomly generates information based on the class, species, and level. Useful for DM's for instant
     * NPC spawning.
     *
     * @param n   Name of the NPC.
     * @param r   The species of the NPC.
     * @param c   The class of the NPC, their stats will be based on the class's favorite attributes.
     * @param lvl The lvl of the NPC.
     */
    public Player(Species r, Classs c, int lvl, boolean printing) {
        this.die = new Dice();
        switch (die.roll(2)) {
            case 1:
                gender = "male";
                break;
            default:
                gender = "female";
                break;
        }
        this.name = randomName(gender,printing);
        if (lvl > 20) {
            System.out.printf("Max level for %s is 20", name);
            lvl = 20;
        }
        if (lvl < 0) {
            System.out.printf("Min level for %s is 1", name);
            lvl = 1;
        }
        this.race = r;
        this.classes = new HashMap<>();
        classes.put(c.name, c);
        this.firstClass = c.name;

        this.level = 0;
        this.exp = 0;

        randomNewAttributes(c, printing);
        initializeForcePoints(c, lvl);
        applySpeciesBonus(r, printing);
        refreshMods();
        applyClasss(c, printing);

        levelUp(c, lvl, printing);

        //testing all feats
        for (String key : featLibrary.keySet())
            addFeat(key, printing);
        initializeFeats(printing);

        if (printing) {
            printSkills();
            printAttributes();
            printSavingThrows();
            printMisc();
            printHealth();
            printCombat();
            printFeats();
        }
    }

    public Player(Classs c1, int c1lvl, Classs c2, int c2lvl, boolean printing) {
        this.die = new Dice();
        switch (die.roll(2)) {
            case 1:
                gender = "male";
                break;
            default:
                gender = "female";
                break;
        }
        //check to see sum is <= 20
        if (c1lvl + c2lvl > 20) {
            System.out.println("Max total level for a player is 20");
            if (c1lvl > c2lvl) {
                c1lvl = 13;
                c2lvl = 7;
            }
            else {
                c1lvl = 7;
                c2lvl = 13;
            }
        }

        classes = new HashMap<>();
        classes.put(c1.name, c1);
        firstClass = c1.name;
        this.race = randomRace();
        this.name = randomName(gender,printing);

        initializeForcePoints(c1, c1lvl);
        this.level = 0;
        this.exp = 0;

        randomNewAttributes(c1, printing);
        applySpeciesBonus(race, printing);
        refreshMods();
        applyClasss(c1, printing);

        levelUp(c1, c1lvl, printing);
        levelUp(c2, c2lvl, printing);

        //testing all feats
        for (String key : featLibrary.keySet())
            addFeat(key, printing);
        initializeFeats(printing);
    }

    //////////////////////////////////FOR RANDOM NPC GENERATION/////////////////////////////////////

    /**
     * Helper method to randomly select a name based on gender. Perhaps it could be improved to make
     * choices based on species, but that is much more complex, maybe I can do that later.
     *
     * @param sex Desired gender for the name.
     * @return The randomly generated first and last name.
     */
    public String randomName(String sex, boolean printing) {
        sex = sex.toLowerCase();
        String firstName, lastName;
        int firstIndex;
        int i = 0;
        FirstName f = FirstName.values()[0];
        firstName = f.getName();
        //randomly pick a name from the selection, but make sure the type is compatible
        while (i < 100) {
            firstIndex = die.roll(FirstName.values().length)-1;
            f = FirstName.values()[firstIndex];
            if (printing)
                System.out.printf("index=%d%nName=%s%nGender=%s%nTargetGender=%s%n",firstIndex,f.getName(),f.getGender(),sex);
            if (f.getGender().startsWith(sex) || f.getGender().contains("n")) {
                firstName = f.getName().replace('_','\'');
                if (printing)
                    System.out.printf("Picked %s for a name. Gender = %s%n", firstName, f.getGender());
                break;
            }
            i++;
        }
        lastName = LastNames.values()[die.roll(FirstName.values().length)-1].getName().replace('_','\'');
        if (printing)
            System.out.printf("Picked %s for a last name.%n", lastName, f.getGender());
        return firstName + " " + lastName;
    }

    /**
     * Outputs a random class based on if the player calling the function is an NPC or a real PC.
     *
     * @param isNPC Whether or not the function should include nonHero classes
     * @return The randomized class
     */
    public Classs randomClasss(boolean isNPC) {
        Classs c;
        int classesToChooseFrom;
        if (isNPC)
            classesToChooseFrom = 10;
        else
            classesToChooseFrom = 9;
        int randomClass = die.roll(classesToChooseFrom);//Currently there are 10 class types
        switch (randomClass) {
            case 1:
                c = new ForceAdept();
                break;
            case 2:
                c = new Fringer();
                break;
            case 3:
                c = new JediConsular();
                break;
            case 4:
                c = new JediGuardian();
                break;
            case 5:
                c = new Noble();
                break;
            case 6:
                c = new Scoundrel();
                break;
            case 7:
                c = new Scout();
                break;
            case 8 :
                c = new Soldier();
                break;
            case 9:
                c = new TechSpecialist();
                break;
            default:
                c = new Thug();
        }
        return c;
    }

    /**Not all Scouts are Ewoks, but Ewoks do have a high tendency towards that calling. This function
     * helps determine compatible class/species pairing.
     *
     * @param s The species helps determine which class the function will lean towards.
     * @return The classs randomly generated based on class.
     */
    private Classs randomClasss(Species s, boolean printing) {
        Classs out;
        //First determine if one of their favorite classes will be chosen. 75% of the time they should pursue their favorite classes.
        if (die.roll(100)  <= 25) {
            out = randomClasss(false);
            if (printing)
                System.out.printf("Choosing a non-favorite class for %s%n", this.name);
        }
        else {
            out = s.getFavoriteClasses()[die.roll(s.getFavoriteClasses().length-1)];
            if (printing)
                System.out.printf("Choosing a favorite class for %s%n", this.name);
        }
        System.out.printf("Randomly chose the %s class for the new %s%n", out.getName(), s.name());
        return out;
    }

    /**
     * Automatically replicates the attribute generation for a first level hero. Normally, the player
     * will roll four d6 and take the sum of the three highest dice, this will range from 8 to 18
     * typically. This process happens six times. To prevent utterly terrible heroes, this method takes
     * the best six of seven of the process.
     */
    private void randomNewAttributes(Classs c, boolean printing) {
        Dice die = new Dice();
        int[] temp = new int[7];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = die.bestOf(3, 4, 6);
        }
        Arrays.sort(temp);

        switch (c.getFav1()) {
            case STR:
                this.STR = temp[6];
                break;
            case DEX:
                this.DEX = temp[6];
                break;
            case CON:
                this.CON = temp[6];
                break;
            case INT:
                this.INT = temp[6];
                break;
            case WIS:
                this.WIS = temp[6];
                break;
            case CHA:
                this.CHA = temp[6];
                break;
        }

        switch (c.getFav2()) {
            case STR:
                this.STR = temp[5];
                break;
            case DEX:
                this.DEX = temp[5];
                break;
            case CON:
                this.CON = temp[5];
                break;
            case INT:
                this.INT = temp[5];
                break;
            case WIS:
                this.WIS = temp[5];
                break;
            case CHA:
                this.CHA = temp[5];
                break;
        }

        switch (c.getFav3()) {
            case STR:
                this.STR = temp[4];
                break;
            case DEX:
                this.DEX = temp[4];
                break;
            case CON:
                this.CON = temp[4];
                break;
            case INT:
                this.INT = temp[4];
                break;
            case WIS:
                this.WIS = temp[4];
                break;
            case CHA:
                this.CHA = temp[4];
                break;
        }

        switch (c.getFav4()) {
            case STR:
                this.STR = temp[3];
                break;
            case DEX:
                this.DEX = temp[3];
                break;
            case CON:
                this.CON = temp[3];
                break;
            case INT:
                this.INT = temp[3];
                break;
            case WIS:
                this.WIS = temp[3];
                break;
            case CHA:
                this.CHA = temp[3];
                break;
        }

        switch (c.getFav5()) {
            case STR:
                this.STR = temp[2];
                break;
            case DEX:
                this.DEX = temp[2];
                break;
            case CON:
                this.CON = temp[2];
                break;
            case INT:
                this.INT = temp[2];
                break;
            case WIS:
                this.WIS = temp[2];
                break;
            case CHA:
                this.CHA = temp[2];
                break;
        }

        switch (c.getFav6()) {
            case STR:
                this.STR = temp[1];
                break;
            case DEX:
                this.DEX = temp[1];
                break;
            case CON:
                this.CON = temp[1];
                break;
            case INT:
                this.INT = temp[1];
                break;
            case WIS:
                this.WIS = temp[1];
                break;
            case CHA:
                this.CHA = temp[1];
                break;
        }

        if (printing)
            System.out.printf("Starting stats for the new %s %s:%n%nSTR=%d%nDEX=%d%nCON=%d%nINT=%d%nWIS=%d%nCHA=%d%n%n", c.getName(), name, STR, DEX, CON, INT, WIS, CHA);
    }

    /**Generates an unbiased race. True random.
     * @return The species randomly generated based on class.
     */
    private Species randomRace() {
        int randomSpecies = die.roll(Species.values().length) - 1;
        return Species.values()[randomSpecies];
    }

    /**
     * Smartly picks attributes to level up, prioritizing odd numbers (to get a modifier change). The
     * Attribute chosen is based on their class and favorite Attributes. If an attribute is already at
     * 18, they will chose another attribute to increment.
     *
     * @param c   the given class to base choices on
     * @param lvl the target level to level up to, ASSUMING THE PLAYER IS GOING UP FROM ZERO
     */
    private void lvlUpAttributes(Classs c, int lvl, boolean printing) {
        //Every 4th level a new attribute point is obtained
        int attributePointsLeft = 0;
        if (lvl % 4 == 0)
            attributePointsLeft = 1;
        boolean incremented;
        if (printing)
            System.out.printf("Leveling up %s's attributes by %d points%n", this.name, attributePointsLeft);
        while (attributePointsLeft > 0) {
            incremented = false;

            //First even out all stats starting with their favorites, then start buffing their favorite stats

            switch (c.getFav1()) {
                case STR:
                    if (this.STR % 2 != 0 ) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 ) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 ) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 ) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 ) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 ) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav2()) {
                case STR:
                    if (this.STR % 2 != 0 ) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 ) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 ) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 ) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 ) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 ) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav3()) {
                case STR:
                    if (this.STR % 2 != 0 ) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 ) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 ) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 ) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 ) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 ) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav4()) {
                case STR:
                    if (this.STR % 2 != 0 ) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 ) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 ) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 ) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 ) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 ) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav5()) {
                case STR:
                    if (this.STR % 2 != 0 ) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 ) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 ) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 ) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 ) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 ) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav6()) {
                case STR:
                    if (this.STR % 2 != 0 ) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 ) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 ) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 ) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 ) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 ) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav1()) {
                //All stats are even, now get the main attribute up to 18.
                case STR:
                    if (this.STR % 2 != 0 || this.STR < 18 && !incremented) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 || this.DEX < 18 && !incremented) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 || this.CON < 18 && !incremented) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 || this.INT < 18 && !incremented) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 || this.WIS < 18 && !incremented) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 || this.CHA < 18 && !incremented) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav2()) {
                //Get the second favorite attribute up to 16.
                case STR:
                    if (this.STR % 2 != 0 || this.STR < 16 && !incremented) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 || this.DEX < 16 && !incremented) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 || this.CON < 16 && !incremented) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 || this.INT < 16 && !incremented) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 || this.WIS < 16 && !incremented) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 || this.CHA < 16 && !incremented) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav3()) {
                //Get the third favorite stat to 14.
                case STR:
                    if (this.STR % 2 != 0 && !incremented && this.STR < 14) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX % 2 != 0 && !incremented && this.DEX < 14) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON % 2 != 0 && !incremented && this.CON < 14) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT % 2 != 0 && !incremented && this.INT < 14) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS % 2 != 0 && !incremented && this.WIS < 14) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA % 2 != 0 && !incremented && this.CHA < 14) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav3()) {
                /**If this is reached, then it means all stats are even, with the top two at
                 * least at 18 and 16. Now we can get the third to 14*/
                case STR:
                    if (this.STR < 14 && !incremented) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX < 14 && !incremented) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON < 14 && !incremented) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT < 14 && !incremented) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS < 14 && !incremented) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA < 14 && !incremented) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav4()) {
                /**If this is reached, then it means the top five stats are even, with the top three at
                 * least at 18, 16, 14. Now we can get the fourth to 12.
                 * */
                case STR:
                    if (this.STR < 12 && !incremented) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX < 12 && !incremented) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON < 12 && !incremented) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT < 12 && !incremented) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS < 12 && !incremented) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA < 12 && !incremented) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav5()) {
                /**If this is reached, then it means the top five stats are even, with the top four at
                 * least at 18, 16, 14, 12. Now we can get the fifth stat to 12
                 * */
                case STR:
                    if (this.STR < 12 && !incremented) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX < 12 && !incremented) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON < 12 && !incremented) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT < 12 && !incremented) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS < 12 && !incremented) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA < 12 && !incremented) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav6()) {
                /**If this is reached, then it means the top five stats are even, with the top five at
                 * least at 18, 16, 14, 12, 12. Now we can get the sixth stat up to 10.
                 * */
                case STR:
                    if (this.STR < 10 || this.STR % 2 != 0 && !incremented) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    if (this.DEX < 10 || this.DEX % 2 != 0 && !incremented) {
                        this.DEX++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    }
                    break;
                case CON:
                    if (this.CON < 10 || this.CON % 2 != 0 && !incremented) {
                        this.CON++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    }

                    break;
                case INT:
                    if (this.INT < 10 || this.INT % 2 != 0 && !incremented) {
                        this.INT++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    }

                    break;
                case WIS:
                    if (this.WIS < 10 || this.WIS % 2 != 0 && !incremented) {
                        this.WIS++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    }

                    break;
                case CHA:
                    if (this.CHA < 10 || this.CHA % 2 != 0 && !incremented) {
                        this.CHA++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    }
                    break;
            }

            if (attributePointsLeft == 0 || incremented)
                continue;

            switch (c.getFav1()) {
                /**If this is somehow reached, then the hero has fantastic starting stats, and it
                 * means the top five stats are even, with the top four at least at 18, 16, 14, 12,
                 * 12, and 10. Now we can max out the best stat.
                 * */
                case STR:
                    if (!incremented) {
                        this.STR++;
                        attributePointsLeft--;
                        incremented = true;
                        if (printing)
                            System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    }

                    break;
                case DEX:
                    this.DEX++;
                    attributePointsLeft--;
                    incremented = true;
                    if (printing)
                        System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    break;
                case CON:
                    this.CON++;
                    attributePointsLeft--;
                    incremented = true;
                    if (printing)
                        System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    break;
                case INT:
                    this.INT++;
                    attributePointsLeft--;
                    incremented = true;
                    if (printing)
                        System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    break;
                case WIS:
                    this.WIS++;
                    attributePointsLeft--;
                    incremented = true;
                    if (printing)
                        System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    break;
                case CHA:
                    this.CHA++;
                    attributePointsLeft--;
                    incremented = true;
                    if (printing)
                        System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    break;
            }

            if (printing) {
                System.out.printf("Reached end of while loop%nSTR=%d%nDEX=%d%nCON=%d%nINT=%d%nWIS=%d%nCHA=%d%n%nPoints Left=%d%n", STR, DEX, CON, INT, WIS, CHA, attributePointsLeft);
            }
        }

        if (printing)
            System.out.printf("Finished leveling up %s's attributes.%n%n", this.name);
        refreshMods();
    }

    /**
     * Method for a single class npc to spend skill points per level
     */
    private void lvlUpNewSkills(Classs c, int lvl, boolean printing) {
        if (lvl == 1 && this.level == 1) {
            if (classes.get(c.name).getSkillUp() + iMod >= 1)
                skillPointsToSpend += ((classes.get(c.name).getSkillUp() + iMod) * 4);
            else
                skillPointsToSpend+=4;
            if (race == Species.HUMAN)
                skillPointsToSpend += 4;
            spendSkillPoints(c, lvl, printing);
        } else {
            if (classes.get(c.name).getSkillUp() + iMod >= 1)
                skillPointsToSpend += (classes.get(c.name).getSkillUp() + iMod);
            else
                skillPointsToSpend++;
            if (race == Species.HUMAN)
                skillPointsToSpend++;
            spendSkillPoints(c, lvl, printing);
        }
    }

    /**
     * Randomly picks one of the unit's class skills and spends a skill point IFF it is allowed to
     * TODO need to consider force feats.
     *
     * @param c The class to level up.
     */
    private void spendSkillPoints(Classs c, int lvl, boolean printing) {
        ArrayList<Integer> classSkills = c.getClassSkills();
        int max = level + 3;
        float crossMax = max / 2;
        int forceMax = forceLevel + 3;
        int skillToIncrement;
        int tryAgain = 0;
        int tryTimes = 10;
        if (printing)
            System.out.printf("Spending %d skill points for %s%n", skillPointsToSpend, name);
        while (skillPointsToSpend > 0) {
            if (c.getName().startsWith("Jedi") || c.getName().startsWith("Force")) {
                //randomly picking one of the class skills
                skillToIncrement = die.roll(classSkills.size() + forceSkills.length) - 1;
                if (printing)
                    System.out.printf("SkillToIncrement: %d PointsLeft: %d%n", skillToIncrement, skillPointsToSpend);
                //This means that a force skill is to be incremented
                if (skillToIncrement >= classSkills.size()) {
                    skillToIncrement -= classSkills.size();
                    if (forceSkills[skillToIncrement].getRank() < forceMax && forceSkills[skillToIncrement].isClassSkill()) {
                        forceSkills[skillToIncrement].addRanks(1, printing);
                        totalRanks++;
                    }
                    else {
                        if (printing)
                            System.out.printf("    %s isn't a class skill, continuing%n", forceSkills[skillToIncrement].getType().getName());
                        continue;
                    }
                } else {
                    //This means a regular skill is chosen for a Force sensitive player
                    if (skills[classSkills.get(skillToIncrement).intValue()].getRank() < max) {
                        skills[classSkills.get(skillToIncrement).intValue()].addRanks(1, printing);
                        totalRanks++;
                    }
                    //If you reach here, then the skill it rolled for is already maxed out, so pick a new non class skill
                    else {
                        if (tryAgain < tryTimes) {
                            if (printing)
                                System.out.printf("Trying again. %s is maxed out%n %d more tries.%n",skills[classSkills.get(skillToIncrement).intValue()].getType().getName(), tryTimes-tryAgain);
                            tryAgain++;
                            continue;
                        }
                        if (skillPointsToSpend < 2)
                            continue;
                        //Randomly choosing ANY skill to level up, possibly another class skill
                        skillToIncrement = die.roll(skills.length);
                        if (printing)
                            System.out.printf("Previous skill was maxed out, now checking %d%n", skillToIncrement);
                        if (skillToIncrement == 7 || skillToIncrement == 8)
                            skillToIncrement = 6;
                        if (skillToIncrement == 23 || skillToIncrement == 24)
                            skillToIncrement = 22;
                        if (skills[skillToIncrement].isClassSkill()) {
                            if (skills[skillToIncrement].getRank() < max) {
                                skills[skillToIncrement].addRanks(1, printing);
                                totalRanks++;
                            }
                        } else { //This means the chosen skill is NOT a class skill
                            if (skills[skillToIncrement].getRank() < crossMax && skillPointsToSpend > 1) {
                                if (printing)
                                    System.out.println("ADDING A NON CLASS SKILL");
                                skills[skillToIncrement].addRanks(1, printing);
                                totalRanks += 2;
                                skillPointsToSpend--;
                            }
                        }
                    }
                }
            }
            else //NON JEDI class
                {
                skillToIncrement = die.roll(classSkills.size()) - 1;
                if (printing)
                    System.out.printf("SkillToIncrement: %d PointsLeft: %d%n", skillToIncrement, skillPointsToSpend);
                //Choosing to increment just one Craft(6) and one Knowledge(22)
                if (skills[classSkills.get(skillToIncrement).intValue()].getRank() < max) {
                    skills[classSkills.get(skillToIncrement).intValue()].addRanks(1, printing);
                    totalRanks++;
                }
                //If you reach here, then the skill it rolled for is already maxed out, so pick a new non class skill
                else {
                    if (tryAgain < tryTimes) {
                        if (printing)
                            System.out.printf("Trying again. %s is maxed out%n %d more tries.%n",skills[classSkills.get(skillToIncrement).intValue()].getType().getName(), tryTimes-tryAgain);
                        tryAgain++;
                        continue;
                    }
                    if (skillPointsToSpend < 2)
                        continue;
                    //Randomly choosing ANY skill to level up, possibly another class skill
                    skillToIncrement = die.roll(skills.length) - 1;
                    if (printing)
                        System.out.printf("Previous skill was maxed out, now checking %d%n", skillToIncrement);
                    if (skillToIncrement == 7 || skillToIncrement == 8)
                        skillToIncrement = 6;
                    if (skillToIncrement == 23 || skillToIncrement == 24)
                        skillToIncrement = 22;
                    if (skills[skillToIncrement].isClassSkill()) {
                        if (skills[skillToIncrement].getRank() < max) {
                            skills[skillToIncrement].addRanks(1, printing);
                            totalRanks++;
                        } else continue;
                    } else { //This means the chosen skill is NOT a class skill
                        if (skills[skillToIncrement].getRank() < crossMax && skillPointsToSpend > 1) {
                            if (printing)
                                System.out.println("ADDING A NON CLASS SKILL");
                            skills[skillToIncrement].addRanks(1, printing);
                            totalRanks += 2;
                            skillPointsToSpend--;
                        }
                    }
                }
            }
            skillPointsToSpend--;
        }
        if (printing) System.out.printf("TotalRanks: %d%n", totalRanks);
    }

    //////////////////////////////////Leveling Up METHODS///////////////////////////////////////////

    /**
     * Helper Method to level up a character one level. For example, for any given level up, you need
     * to update saves, base attack, class defense, reputation bonus, spend new skill points, gain
     * vitality points, and obtain new abilities/powers or bonus feats based on your class.
     * TODO NEED TO DO FEATS AND POWERS/ABILITIES AND MULTICLASS
     *
     * @param c        The chosen Classs to level up
     * @param levels   The amount of levels to level up
     * @param printing If true, print out extra data for debugging
     */
    protected void levelUp(Classs c, int levels, boolean printing) {
        int currentLvl;
        boolean safe;

        //First check to see if the player has any levels in the chosen class to lvlup. If it doesn't,
        //add that to the classmap.
        if (!classes.containsValue(c)) {
            classes.put(c.name, c);
            currentLvl = 0;
        }
        else {
            currentLvl = classes.get(c.name).getClassLvl();
        }
        while (levels > 0) {
            if (c.isForce) this.forceLevel++;
            currentLvl++;
            level++;
            if (printing)
                System.out.printf("%n%nLeveling up %s from %d to %d in the %s class%nTotal level: %d%n", name, c.classLvl, currentLvl, c.getName(), level);
            lvlUpAttributes(c, level, printing);
            lvlUpNewSkills(c, currentLvl, printing);
            refreshBaseValues(c, currentLvl, printing);
            safe = level %2 != 0;//if level is odd, go safe, otherwise roll a die.
            lvlUpVitality(c, safe, printing);
            clearBuffs();
            refreshCombat();
            refreshInitiative();
            refreshDefense();
            refreshMods();
            refreshSaves();
            updateForcePoints();
            classes.get(c.name).classLvlUp();
            updateExp(level, printing);
            //TODO NEED TO DO FEATS AND ABILITIES/POWERS

            if (printing) {
                System.out.printf("%nFinished leveling up %s to %d in the %s class%n%nTotal level: %d%n", name, currentLvl, c.getName(), level);
                printAttributes();
                printSavingThrows();
                printSkills();
                printMisc();
                printHealth();
                printCombat();
                printFeats();
            }
            levels--;
        }
    }

    /**
     * Updates the experience points based on level.
     * @param lvl The level to update to
     * @param printing Whether to print or not for debugging purposes
     */
    public void updateExp(int lvl, boolean printing) {
        if (lvl == 2){
            exp += 1000;
        }
        else {
            exp += (lvl - 1)*1000;
            if (printing) {
                System.out.printf("%nExp at level %d=%d%n", lvl, exp);
            }
        }
    }

    /**
     * Adds vitality to the max vitality score, based on class. A hero can choose to add an average, or
     * safe, amount of vitality if they don't want to roll for it. That method is addSafeVitality(), shown
     * below this method.
     * @param c The class the hero is leveling up.
     * @param safe True if safely rolling, false if feeling lucky.
     * @param printing Used for debugging.
     */
    private void lvlUpVitality(Classs c, boolean safe, boolean printing) {
        if (level == 1) {
            initializeVitalityWoundPoints(c, printing);
            return;
        }
        int added;
        added = safe ? addSafeVitality(c) : die.roll(c.getVitalityUp());
        vitalityRolls += added;
        maxVP += added + cMod < 1 ? 1 : added;
        maxWP = CON;
        currentWP = maxWP;
        currentVP = maxVP;
        if (printing) {
            if (safe) {
                if (added != 1)
                    System.out.printf("%nSafely rolled %d + CON mod: %d = %d maxVP: %d", added, cMod, added + cMod, maxVP);
                else
                    System.out.printf("%nGet stronger, your CON is trash and hurt your safe roll of %d, bringing it to 1. maxVP: %d%n", added, maxVP);
            }
            else {
                if (added != 1)
                    System.out.printf("%nRisked it and rolled %d + CON mod: %d = %d maxVP: %d", added, cMod, added + cMod, maxVP);
                else
                    System.out.printf("%nGet stronger, your CON is trash and hurt your risky roll of %d, bringing it to 1. maxVP: %d%n", added, maxVP);
            }
        }
    }

    /**
     * If you don't want to risk getting a low roll on your new vitality, a player can choose to add
     * a safe amount of vitality based on their total level. On odd levels, you add the half roll rounded
     * up. On even levels, you take the half roll. For example, Scouts roll a d8 for vitality. Say Scout
     * Chewy turns lvl 13, and his CON is 15. He chooses to safely gain 5 + 2 new vitality; 5 is 8(die)/2 + 1,
     * and 2 from his CON score of 15. Next level at 14, he chooses to safely gain vitality, it would be 4 + 2.
     * @param c The class taking a level in.
     * @return The safe vitality roll, excluding the CON modifier.
     */
    private int addSafeVitality(Classs c) { return this.level %2 != 0 ? c.getVitalityUp()/2 + 1 : c.getVitalityUp()/2; }

    //////////////////////////////////YOCO: You only call once//////////////////////////////////////

    /**
     * Applies all the bonuses from a given species to the hero. ONLY EVER CALLED ONCE AND ONLY ONCE
     * Thusly, it calls the populateFeatLibrary method as that method should only be called once.
     * TODO need add specific Feats and Abilities for species.
     *
     * @param s The type of Species to derive bonuses from.
     */
    private void applySpeciesBonus(Species s, boolean printing) {
        languages = s.getLang();
        homeworld = s.getHomeworld();
        populateFeatLibrary(printing);

        STR += s.getStrMod();
        DEX += s.getDexMod();
        CON += s.getConMod();
        INT += s.getIntMod();
        WIS += s.getWisMod();
        CHA += s.getChaMod();

        fortitudeMiscMod += s.getFortMod();
        reflexMiscMod += s.getReflexMod();
        willMiscMod += s.getWillMod();

        speed = s.getSpeed();
        sizeMod = s.getSize();
        initiativeMiscBonus += s.getInit();
        defenseMiscBonus += s.getDef();

        if (printing)
            System.out.printf("After applying %s bonus:%n%nSTR=%d%nDEX=%d%nCON=%d%nINT=%d%nWIS=%d%nCHA=%d%n%n", race.name(), STR, DEX, CON, INT, WIS, CHA);
    }

    /**
     * Applies the Classs saves, defense bonus, etc to the player. Called once when the player object
     * is initialized.
     *
     * @param c The specific Classs being applied.
     */
    private void applyClasss(Classs c, boolean printing) {
        if (printing)
            System.out.printf("Applying %s class for %s%n", c.getName(), this.name);
        vitalityDie = c.getVitalityUp();
        credits = die.roll(c.getCashDie()) * c.getCash();// A Jedi Consular starts of with 1d4 * 250
        //refreshBaseValues(c, 1, printing);
        populateSkills(c);
        applySkillMods();
    }

    /**
     * Populates the skill trees
     */
    private void populateSkills(Classs c) {
        Skill s = new Skill();
        skills = s.populateSkills(c);
        forceSkills = s.populateForceSkills();
    }

    /**
     * Initializing Vitality and Wound Points for a level one hero, ONLY CALLED ONCE.
     **/
    private void initializeVitalityWoundPoints(Classs c, boolean printing) {
        maxVP = c.getVitalityUp() + cMod;
        currentVP = maxVP;
        maxWP = CON;
        currentWP = maxWP;
        if (printing)
            System.out.printf("%nWound Points initialized. Die: %d CON Mod: %d Max VP: %d%n",c.getVitalityUp(), cMod, maxVP);
    }

    ////////////////////////////////////////Refreshers//////////////////////////////////////////////

    /**
     * Helper methods to refresh specific stats, factoring in any miscellaneous bonuses
     * For some reason it isn't making a stat of 9 into a mod of -1, rather it is 0.
     */
    private void refreshStr(int bonus) {
        if (STR % 2 == 0)
            this.sMod = (STR - 10) / 2 + bonus;
        else
            this.sMod = (STR - 11) / 2 + bonus;
    }

    private void refreshDex(int bonus) {
        if (DEX % 2 == 0)
            this.dMod = (DEX - 10) / 2 + bonus;
        else
            this.dMod = (DEX - 11) / 2 + bonus;
    }

    private void refreshCon(int bonus) {
        if (CON % 2 == 0)
            this.cMod = (CON - 10) / 2 + bonus;
        else
            this.cMod = (CON - 11) / 2 + bonus;
    }

    private void refreshInt(int bonus) {
        if (INT % 2 == 0)
            this.iMod = (INT - 10) / 2 + bonus;
        else
            this.iMod = (INT - 11) / 2 + bonus;
    }

    private void refreshWis(int bonus) {
        if (WIS % 2 == 0)
            this.wMod = (WIS - 10) / 2 + bonus;
        else
            this.wMod = (WIS - 11) / 2 + bonus;
    }

    private void refreshCha(int bonus) {
        if (CHA % 2 == 0)
            this.chMod = (CHA - 10) / 2 + bonus;
        else
            this.chMod = (CHA - 11) / 2 + bonus;
    }

    /**
     * Applies all the modifiers in the skill arrays.
     */
    private void applySkillMods() {
        for (Skill s : skills) {
            switch (s.getType().getAttribute()) {
                case STR:
                    s.setAttributeModifier(sMod);
                    break;
                case DEX:
                    s.setAttributeModifier(dMod);
                    break;
                case CON:
                    s.setAttributeModifier(cMod);
                    break;
                case INT:
                    s.setAttributeModifier(iMod);
                    break;
                case WIS:
                    s.setAttributeModifier(wMod);
                    break;
                case CHA:
                    s.setAttributeModifier(chMod);
                    break;
            }
        }

        /**
         * In the standard core rulebook, there doesn't exist any Force Skills that are based on STR
         * or DEX, so the first two cases are superfluous.
         */
        for (Skill s : forceSkills) {
            switch (s.getType().getAttribute()) {
                case STR:
                    s.setAttributeModifier(sMod);
                    break;
                case DEX:
                    s.setAttributeModifier(dMod);
                    break;
                case CON:
                    s.setAttributeModifier(cMod);
                    break;
                case INT:
                    s.setAttributeModifier(iMod);
                    break;
                case WIS:
                    s.setAttributeModifier(wMod);
                    break;
                case CHA:
                    s.setAttributeModifier(chMod);
                    break;
            }
        }
    }

    /**
     * Applies the 10 + classDefBonus + dMod + dexTempMod + sizeMod + defenseMiscBonus formula
     */
    private void refreshDefense() {
        refreshDex(dexTempMod);
        if (classes.size() == 1)
            classPenalty = 0;
        else {
            classPenalty = -(classes.size() - 1)*2;//TODO NEED TO ACCOUNT FOR PRESTIGE
        }
        defense = 10 + classDefBonus + dMod + sizeMod + defenseMiscBonus + dodgeBonus + classPenalty;
    }

    /**
     * This is on base 1 for simplicity, the individual methods change the numbers from base 1 to 0.
     * Sets all the base values based on a specific level of a particular class, for example a lvl 6
     * Jedi Guardian is going to have different base values than a lvl 8 Scoundrel.
     * TODO need to update this for multiclass functionality, possibly by overloading with two classes
     * and their relative levels?
     *
     * @param lvl Updates the values to a specific level of a certain class
     */
    private void refreshBaseValues(Classs c, int lvl, boolean printing) {
        if (!c.prestige) {
            if (lvl == 1) {
                bab += c.getBABValue(lvl);
                baseFortitude += c.getFortSaveValue(lvl);
                baseReflex += c.getRefSaveValue(lvl);
                baseWill += c.getWillSaveValue(lvl);
                classDefBonus += c.getDefenseValue(lvl);
                reputation += c.getReputationValue(lvl);
            } else {
                bab += c.getBABValue(lvl) - c.getBABValue(lvl - 1);
                baseFortitude += c.getFortSaveValue(lvl) - c.getFortSaveValue(lvl - 1);
                baseReflex += c.getRefSaveValue(lvl) - c.getRefSaveValue(lvl - 1);
                baseWill += c.getWillSaveValue(lvl) - c.getWillSaveValue(lvl - 1);
                classDefBonus += c.getDefenseValue(lvl) - c.getDefenseValue(lvl - 1);
                reputation += c.getReputationValue(lvl) - c.getReputationValue(lvl - 1);
            }
        }
        //Prestige classes
        else {
            if (lvl == 1) {
                bab += c.getBABValue(lvl);
                baseFortitude += c.getFortSaveValue(lvl);
                baseReflex += c.getRefSaveValue(lvl);
                baseWill += c.getWillSaveValue(lvl);
                classDefBonus += c.getDefenseValue(lvl);
                reputation += c.getReputationValue(lvl);
            } else {
                bab += c.getBABValue(lvl) - c.getBABValue(lvl - 1);
                baseFortitude += c.getFortSaveValue(lvl) - c.getFortSaveValue(lvl - 1);
                baseReflex += c.getRefSaveValue(lvl) - c.getRefSaveValue(lvl - 1);
                baseWill += c.getWillSaveValue(lvl) - c.getWillSaveValue(lvl - 1);
                classDefBonus += c.getDefenseValue(lvl) - c.getDefenseValue(lvl - 1);
                reputation += c.getReputationValue(lvl) - c.getReputationValue(lvl - 1);
            }
        }
        refreshSaves();
    }

    /**
     * Refreshes the saving throw values.
     */
    private void refreshSaves() {
        fortitude = baseFortitude + cMod + conTempMod + fortitudeMiscMod;
        reflex = baseReflex + dMod + dexTempMod + reflexMiscMod;
        will = baseWill + wMod + wisTempMod + willMiscMod;
    }

    /**
     * Refreshes the initiative.
     */
    private void refreshInitiative() {
        initiative = dMod + dexTempMod + initiativeMiscBonus;
    }

    /**
     * Refreshes the combat fields.
     */
    private void refreshCombat() {
        //TODO armor check penalty
        melee = bab + sMod + strTempMod + sizeMod + meleeMiscMod;
        ranged = bab + dMod + dexTempMod + sizeMod + rangedMiscMod;
    }

    /**
     * Refreshes the modifiers with the temporary buffs
     */
    private void refreshMods() {
        refreshStr(strTempMod);
        refreshDex(dexTempMod);
        refreshCon(conTempMod);
        refreshInt(intTempMod);
        refreshWis(wisTempMod);
        refreshCha(chaTempMod);
    }

    /**
     * Refreshes the modifiers back to the original state and removes buffs
     */
    private void clearBuffs() {
        refreshStr(0);
        refreshDex(0);
        refreshCon(0);
        refreshInt(0);
        refreshWis(0);
        refreshCha(0);

        isStrBuffed = false;
        isDexBuffed = false;
        isConBuffed = false;
        isIntBuffed = false;
        isWisBuffed = false;
        isChaBuffed = false;

        applySkillMods();
    }

    /**
     * Starts a hero off with the appropriate amount of Force Points. Only Force users can have more
     * than four Force points at a time.
     *
     * @param c   The Class of the hero
     * @param lvl The starting lvl of the hero
     */
    private void initializeForcePoints(Classs c, int lvl) {
        switch (c.getName()) {
            case "ForceAdept":
                forcePoints = lvl;
                break;
            case "JediConsular":
                forcePoints = lvl;
                break;
            case "JediGuardian":
                forcePoints = lvl;
                break;
            default:
                if (lvl >= 5)
                    forcePoints = 5;
                else
                    forcePoints = lvl;
        }
        darkSidePoints = 0;
    }

    private void updateForcePoints() {
        if (forceLevel > 0 || forcePoints < 4)
            forcePoints++;
    }

    /////////////////////////////////////////////FEATS//////////////////////////////////////////////

    /**
     * Only called once within the applySpecies method, which is only ever called once, this populates a
     * library of all the feat objects and stores them in the HashMap<String, Feat> featLibrary. There,
     * a player has access to instances of all feat objects.
     *
     * @param printing If true, the entire library will be printed out to the System console.
     */
    private void populateFeatLibrary(boolean printing) {
        featLibrary = new HashMap<>();
        this.feats = new HashMap<>();
        featLibrary.put("ACROBATIC", new Acrobatic());
        featLibrary.put("ADVANCED MARTIAL ARTS", new AdvancedMartialArts());
        featLibrary.put("ALERTNESS", new Alertness());
        featLibrary.put("ALTER", new Alter());
        featLibrary.put("AMBIDEXTERITY", new Ambidexterity());
        featLibrary.put("ANIMAL AFFINITY", new AnimalAffinity());
        featLibrary.put("ARMOR PROFICIENCY HEAVY", new ArmorProficiencyHeavy());
        featLibrary.put("ARMOR PROFICIENCY LIGHT", new ArmorProficiencyLight());
        featLibrary.put("ARMOR PROFICIENCY MEDIUM", new ArmorProficiencyMedium());
        featLibrary.put("ARMOR PROFICIENCY POWERED", new ArmorProficiencyPowered());
        featLibrary.put("ATHLETIC", new Athletic());
        featLibrary.put("ATTUNED", new Attuned());
        featLibrary.put("AWARE", new Aware());
        featLibrary.put("BLIND FIGHT", new BlindFight());
        featLibrary.put("BURST OF SPEED", new BurstOfSpeed());
        featLibrary.put("CAUTIOUS", new Cautious());
        featLibrary.put("CLEAVE", new Cleave());
        featLibrary.put("COMBAT EXPERTISE", new CombatExpertise());
        featLibrary.put("COMBAT REFLEXES", new CombatReflexes());
        featLibrary.put("COMPASSION", new Compassion());
        featLibrary.put("DEFENSIVE MARTIAL ARTS", new DefensiveMartialArts());
        featLibrary.put("DISSIPATE ENERGY", new DissipateEnergy());
        featLibrary.put("DODGE", new Dodge());
        featLibrary.put("ENDURANCE", new Endurance());
        featLibrary.put("EXOTIC WEAPON PROFICIENCY", new ExoticWeaponProficiency("Not Set"));
        featLibrary.put("FAME", new Fame());
        featLibrary.put("FAR SHOT", new FarShot());
        featLibrary.put("FOCUS", new Focus());
        featLibrary.put("FORCE FLIGHT", new ForceFlight());
        featLibrary.put("FORCE MASTERY", new ForceMastery());
        featLibrary.put("FORCE MIND", new ForceMind());
        featLibrary.put("FORCE SENSITIVE", new ForceSensitive());
        featLibrary.put("FORCE SPEED", new ForceSpeed());
        featLibrary.put("FORCE WHIRLWIND", new ForceWhirlwind());
        featLibrary.put("FRIGHTFUL PRESENCE", new FrightfulPresence());
        featLibrary.put("GEARHEAD", new Gearhead());
        featLibrary.put("GREAT CLEAVE", new GreatCleave());
        featLibrary.put("GREAT FORTITUDE", new GreatFortitude());
        featLibrary.put("HEADSTRONG", new Headstrong());
        featLibrary.put("HEROIC SURGE", new HeroicSurge());
        featLibrary.put("HIGH FORCE MASTERY", new HighForceMastery());
        featLibrary.put("IMPROVED BANTHA RUSH", new ImprovedBanthaRush());
        featLibrary.put("IMPROVED CRITICAL", new ImprovedCritical("Not Set", new Feat("BLANK", "One empty boi.")));
        featLibrary.put("IMPROVED DISARM", new ImprovedDisarm());
        featLibrary.put("IMPROVED FORCE MIND", new ImprovedForceMind());
        featLibrary.put("IMPROVED INITIATIVE", new ImprovedInitiative());

        if (printing)
            for (String key : featLibrary.keySet()) {
                System.out.println(key + " is in the featLibrary");
            }
    }

    /**
     * Iterates through each feat to check if they add misc bonuses to skills. If they don't add bonuses,
     * it just passes by them to the next feat.
     * @param printing Used for debugging purposes. Determines whether or not to print extra data to
     *                 the console.
     */
    private void initializeFeats(boolean printing) {
        for (String key : feats.keySet()) {
            //check to see if the feat boosts skills
            if (feats.get(key).areSkillsBoosted()) {
                for (int i = 0; i < feats.get(key).getSkills().length; i++) {
                    //Loop through the array held by the Feat, inside there is information on which skills are boosted.
                    //Go through and add the bonuses to the corresponding misc bonus in the player skill array.
                    if (printing && feats.get(key).getSkills()[i] > 0)
                        System.out.printf("Adding %d misc bonus to %s%n",feats.get(key).getSkills()[i], skills[i].getType().getName());
                    skills[i].addMiscMod(feats.get(key).getSkills()[i]);
                }
            }
            //check to see if the Feat boosts Force skills
            else if (feats.get(key).areFSkillsBoosted()) {
                for (int i = 0; i < feats.get(key).getForceSkills().length; i++) {
                    //Loop through the array held by the Feat, inside there is information on which skills are boosted.
                    //Go through and add the bonuses to the corresponding misc bonus in the player skill array.
                    if (printing && feats.get(key).getForceSkills()[i] > 0)
                        System.out.printf("Adding %d misc bonus to %s%n",feats.get(key).getForceSkills()[i], forceSkills[i].getType().getName());
                    forceSkills[i].addMiscMod(feats.get(key).getForceSkills()[i]);
                }
            }
            //Alter force skills = 0, 2, 9, 10, 12, 14, 16, 17
            if (key.contains("Alter")) {
                forceSkills[0].setClassSkill(true);
                forceSkills[2].setClassSkill(true);
                forceSkills[9].setClassSkill(true);
                forceSkills[10].setClassSkill(true);
                forceSkills[12].setClassSkill(true);
                forceSkills[14].setClassSkill(true);
                forceSkills[16].setClassSkill(true);
                forceSkills[17].setClassSkill(true);
            }
            //Control force skills = 1, 8, 11, 15
            if (key.contains("Control")) {
                forceSkills[1].setClassSkill(true);
                forceSkills[8].setClassSkill(true);
                forceSkills[11].setClassSkill(true);
                forceSkills[15].setClassSkill(true);
            }
            //Sense force skills = 5, 6, 7, 18, 19
            if (key.contains("Sense")) {
                forceSkills[5].setClassSkill(true);
                forceSkills[6].setClassSkill(true);
                forceSkills[7].setClassSkill(true);
                forceSkills[18].setClassSkill(true);
                forceSkills[19].setClassSkill(true);
            }
            if (feats.get(key).getMiscBonus() > 0)
                switch (key) {
                    case "DODGE":
                        dodgeBonus+=feats.get(key).getMiscBonus();
                        break;
                    case "GREAT FORTITUDE":
                        fortitudeMiscMod+=feats.get(key).getMiscBonus();
                    case "HEADSTRONG":
                        willMiscMod+=feats.get(key).getMiscBonus();
                    case "IMPROVED CRITICAL":
                        //TODO need to do when I do weapons, probably something like this:
                        //weaponRack(feats.get(key).getSpecialty()).increaseCritical(feats.get(key).getMiscBonus());
                        break;
                    case "IMPROVED INITIATIVE":
                        initiativeMiscBonus+=4;
                        break;
                }
            System.out.printf("%s : %s%n", key, feats.get(key).getInfo());
        }
    }

    /**
     * Determines whether or not a player meets the prerequisites for a given Feat f. Prerequisites are
     * in the form of other feats, or attributes and a determined integer amount, for example Tumble 10.
     * If a feat is required, the prereqs Hashmap<String, Integer> will have the required feat as a String,
     * then "1" for the integer value. If the integer is 0, then that means the listed feat must NOT be taken,
     * for example Infamy requires the player to NOT have the Fame feat.
     *
     * @param f the feat in question
     * @return True if the player meets all prerequisites, false otherwise.
     */
    private boolean meetsPrereqs(Feat f, boolean printing) {
        boolean out = false;
        StringBuilder requirements = new StringBuilder("");
        HashMap<String,Integer> map = f.getPrereqs();
        String key;
        int reqValue, skillIndex;
        if (printing)
            System.out.printf("Checking %s's prereqs for feat %s%n", this.name, f.getName());
        if (f.getPrereqs().size() == 0)
            out = true;
        for (Object k : map.keySet()) {
            key = k.toString().toUpperCase();
            reqValue = map.get(k);
            //Determining if the prereq is a feat or an attribute, the below case means it's the latter
            if (key.length() == 3 && !key.contains("Run")) {
                switch (key) {
                    case "STR":
                        if (STR >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient " + key + ". Required: " + reqValue + " Has " + STR + "\n");
                            out = false;
                        }
                        break;

                    case "DEX":
                        if (DEX >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient " + key + ". Required: " + reqValue + " Has " + DEX + "\n");
                            out = false;
                        }
                        break;

                    case "CON":
                        if (CON >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient " + key + ". Required: " + reqValue + " Has " + CON + "\n");
                            out = false;
                        }
                        break;

                    case "INT":
                        if (INT >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient " + key + ". Required: " + reqValue + " Has " + INT + "\n");
                            out = false;
                        }
                        break;

                    case "WIS":
                        if (WIS >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient " + key + ". Required: " + reqValue + " Has " + WIS + "\n");
                            out = false;
                        }
                        break;

                    case "CHA":
                        if (CHA >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient " + key + ". Required: " + reqValue + " Has " + CHA + "\n");
                            out = false;
                        }
                        break;

                    case "BAB":
                        if (bab >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient " + key + ". Required: " + reqValue + " Has " + bab + "\n");
                            out = false;
                        }
                        break;
                }
            }
            //Check to see if the key is a number, indicating an index of a skill, which means that the
            //value of the key (integer value) represents the required ranks in said skill
            else try {
                skillIndex = Integer.parseInt(key.toString());
                //check to see if the skill is a regular or force skill. Below means it's a regular skill
                if (skillIndex < skills.length) {
                    if (skills[skillIndex].getRank() >= reqValue)
                        out = true;
                    else {
                        requirements.append("   Insufficient " + skills[skillIndex].getType().getName() + ". Required: " + reqValue + " Has " + (int) skills[skillIndex].getRank() + "\n");
                        out = false;
                    }
                }
                //Force Skill
                else {
                    //adjusting index to fit in the Force Skill array
                    skillIndex-=skills.length;
                    if (forceSkills[skillIndex].getRank() >= reqValue)
                        out = true;
                    else {
                        requirements.append("   Insufficient " + forceSkills[skillIndex].getType().getName() + ". Required: " + reqValue + " Has " + (int) forceSkills[skillIndex].getRank() + "\n");
                        out = false;
                    }
                }
            }
            //If this is reached, it means that the String was longer than 3 char, isn't a skill,
            //and is therefore a Feat, so check to see if the feat exists in this objects' feat map.
            catch (NumberFormatException e) {
                if (key.startsWith("FORCE LEVEL")) {
                    if (forceLevel >= reqValue) {
                        out = true;
                        continue;
                    }
                    else {
                        out = false;
                        requirements.append("   Insufficient force level. Required: " + reqValue + " Has: " + forceLevel +  "\n");
                        continue;
                    }
                }
                if (((feats.containsKey(key)) && (map.get(key) == 1)) || ((!feats.containsKey(key)) && (map.get(key) == 0))) {
                    out = true;
                }
                else {
                    out = false;
                    requirements.append("   " + key + "\n");
                }
            }
        }
        if (out && printing)
            System.out.printf("%s meets all prereqs for feat %s%n", this.name, f.getName());
        else if (printing)
            System.out.printf("%s does not meet all prereqs for feat %s%nRequired:%n%s%n", this.name, f.getName(), requirements);

        return out;
    }

    /**
     * Tries to add a feat for a player, first checking if the prerequisites are met for said feat.
     * @param s The String name of the feat, used to access the feat library hashmap as a key.
     * @param printing Used for debugging
     * @return true if the feat is successfully added, false otherwise.
     */
    private boolean addFeat(String s, boolean printing) {
        Feat f = featLibrary.get(s);
        boolean out = false;
        if (meetsPrereqs(f, printing)) {
            out = true;
            feats.put(s,f);
            if (printing)
                System.out.printf("     Successfully added %s to %s's feats.%n%n", s, this.name);
        }
        else if (printing)
            System.out.printf("     Unable to add the %s feat for %s. Try another feat which prerequisites can be met.%n%n",s, this.name);
        return out;
    }

    ///////////////////////////////////////////TESTER METHODS///////////////////////////////////////


    /**
     * Prints out the list of Class skills for the Class object calling this method.
     */
    public void printSkills() {
        System.out.printf("Skill Modifiers for %s are as such:%n", name);
        System.out.println("Skill Name---------------AtMod-Ranks-Misc-Total Skill Modifier");
        String preBuff, attBuff, doubleDigits;
        for (Skill temp : skills) {
            preBuff = temp.getType().getName();
            while (preBuff.length() < 19)
                preBuff += " ";
            if (temp.getAttributeModifier() < 0) attBuff = "";
            else attBuff = " ";
            if (temp.getRank() > 9) doubleDigits = "";
            else doubleDigits = " ";
            System.out.printf("%s(%s): %d%s   %.0f%s   %d    %d%n", preBuff, temp.getType().getAttribute(), temp.getAttributeModifier(), attBuff, temp.getRank(), doubleDigits, temp.getMisc(), temp.getTotalModifier());
        }
        if (forceLevel > 0) {
            System.out.printf("%nForce Class Skills for %s are as such:%n", name);
            for (Skill temp : forceSkills) {
                preBuff = temp.getType().getName();
                while (preBuff.length() < 19)
                    preBuff += " ";
                if (temp.getAttributeModifier() < 0) attBuff = "";
                else attBuff = " ";
                if (temp.getRank() > 9) doubleDigits = "";
                else doubleDigits = " ";
                System.out.printf("%s(%s): %d%s   %.0f%s   %d    %d%n", preBuff, temp.getType().getAttribute(), temp.getAttributeModifier(), attBuff, temp.getRank(), doubleDigits, temp.getMisc(), temp.getTotalModifier());
            }
        }
        System.out.printf("Total Ranks = %d%n",totalRanks);
    }

    /**
     * Print Attributes as they would appear on the character sheet
     */
    public void printAttributes() {
        String buffer = "";
        String modBuffer = " ";
        System.out.printf("%s's%n     Attributes-Modifier-TempScore-TempMod%n", name);
        if (STR < 10) {
            buffer = " ";
            modBuffer = "";
        }
        System.out.printf("STR = %d%s       %d%s        %d         %d       %n", STR, buffer, sMod, modBuffer, strTempScore, strTempMod);
        if (DEX < 10) {
            buffer = " ";
            modBuffer = "";
        } else {
            buffer = "";
            modBuffer = " ";
        }
        System.out.printf("DEX = %d%s       %d%s        %d         %d       %n", DEX, buffer, dMod, modBuffer, dexTempScore, dexTempMod);
        if (CON < 10) {
            buffer = " ";
            modBuffer = "";
        } else {
            buffer = "";
            modBuffer = " ";
        }
        System.out.printf("CON = %d%s       %d%s        %d         %d       %n", CON, buffer, cMod, modBuffer, conTempScore, conTempMod);
        if (INT < 10) {
            buffer = " ";
            modBuffer = "";
        } else {
            buffer = "";
            modBuffer = " ";
        }
        System.out.printf("INT = %d%s       %d%s        %d         %d       %n", INT, buffer, iMod, modBuffer, intTempScore, intTempMod);
        if (WIS < 10) {
            buffer = " ";
            modBuffer = "";
        } else {
            buffer = "";
            modBuffer = " ";
        }
        System.out.printf("WIS = %d%s       %d%s        %d         %d       %n", WIS, buffer, wMod, modBuffer, wisTempScore, wisTempMod);
        if (CHA < 10) {
            buffer = " ";
            modBuffer = "";
        } else {
            buffer = "";
            modBuffer = " ";
        }
        System.out.printf("CHA = %d%s       %d%s        %d         %d       %n%n", CHA, buffer, chMod, modBuffer, chaTempScore, chaTempMod);
    }

    /**
     * Prints out the saving throws.
     */
    public void printSavingThrows() {
        String buffer = " ";
        String modBuffer = " ";
        String baseBuff = " ";
        System.out.println("SAVING THROWS-TOTAL-BASE-ATTMOD-MSCMOD");
        if (fortitude > 9 || fortitude < 0) buffer = "";
        if (cMod < 0) modBuffer = "";
        if (baseFortitude > 9) baseBuff = "";
        System.out.printf("FORTITUDE       %d%s = %d%s  + %d%s  + %d%n", fortitude, buffer, baseFortitude, baseBuff, cMod, modBuffer, fortitudeMiscMod);
        if (reflex > 9 || reflex < 0) buffer = "";
        else buffer = " ";
        if (dMod < 0) modBuffer = "";
        else modBuffer = " ";
        if (baseReflex > 9) baseBuff = "";
        else baseBuff = " ";
        System.out.printf("REFLEX          %d%s = %d%s  + %d%s  + %d%n", reflex, buffer, baseReflex, baseBuff, dMod, modBuffer, reflexMiscMod);
        if (will > 9 || will < 0) buffer = "";
        else buffer = " ";
        if (wMod < 0) modBuffer = "";
        else modBuffer = " ";
        if (baseWill > 9) baseBuff = "";
        else baseBuff = " ";
        System.out.printf("WILL            %d%s = %d%s  + %d%s  + %d%n", will, buffer, baseWill, baseBuff, wMod, modBuffer, willMiscMod);
    }

    public void printMisc() {
        String listOfClasses = "";
        for (String key : classes.keySet())
            listOfClasses += String.format(key + " %d ", classes.get(key).classLvl);
        System.out.printf("%nCLASS(ES)  = %s%n" +
                            "EXP        = %d%n" +
                            "CREDITS    = %d%n" +
                            "LEVEL      = %d%n" +
                            "FORCE LEVEL= %d%n" +
                            "GENDER     = %s%n" +
                            "SPECIES    = %s%n", listOfClasses, exp, credits, level, forceLevel, gender, race.name());
    }

    public void printHealth() {
        System.out.printf("%nMAX VITALITY = %d%nCURRENT VP   = %d%nWOUND POINTS = %d%n", maxVP, currentVP, currentWP);
    }

    public void printCombat() {
        String mTotalBuff, baseBuff, strBuff, sizeBuff, rTotalBuff, dexBuff;
        if (melee < 10 && melee >= 0) mTotalBuff = " ";
        else mTotalBuff = "";
        if (bab < 10) baseBuff = " ";
        else baseBuff = "";
        if (sMod >= 0 && sMod < 10) strBuff = " ";
        else strBuff = "";
        if (sizeMod < 0) sizeBuff = "";
        else sizeBuff = " ";
        System.out.printf("%nMelee Attack Bonus = Base + Str Mod + Size Mod + Misc%n");
        System.out.printf("%d%s                 = %d%s   + %d%s      + %d%s       + %d%n",melee, mTotalBuff, bab, baseBuff, sMod, strBuff, sizeMod, sizeBuff, meleeMiscMod);

        if (ranged < 10 && ranged >= 0) rTotalBuff = " ";
        else rTotalBuff = "";
        if (dMod >= 0 && dMod < 10) dexBuff = " ";
        else dexBuff = "";
        System.out.println("Range Attack Bonus = Base + Dex Mod + Size Mod + Misc");
        System.out.printf("%d%s                 = %d%s   + %d%s      + %d%s       + %d%n%n",ranged, rTotalBuff, bab, baseBuff, dMod, dexBuff, sizeMod, sizeBuff, rangedMiscMod);
        if (classDefBonus > 9) baseBuff = "";
        else baseBuff = " ";
        System.out.println("Defense            = 10   + Class + Dex Mod + Size Mod + Misc Mod + Dodge Bonus + Multi Penalty");
        System.out.printf("%d                 = 10   + %d%s    + %d%s      + %d%s       + %d        + %d           + %d%n%n",defense, classDefBonus, baseBuff, dMod, dexBuff, sizeMod, sizeBuff, defenseMiscBonus, dodgeBonus, classPenalty);
        System.out.println("Initiative         = DexMod + Misc");
        if (initiative < 0) baseBuff = "";
        else baseBuff = " ";
        System.out.printf("%d%s                 = %d%s     + %d%n",initiative, baseBuff, dMod, dexBuff, initiativeMiscBonus);
    }

    public void printFeats() {
        System.out.println("FEATS");
        for (String key : feats.keySet()) {
            if (feats.get(key).getSpecialty().length() != 0)
                System.out.printf("%s (%s)%n", key, feats.get(key).getSpecialty());
            else System.out.println(key);
        }
    }
}
