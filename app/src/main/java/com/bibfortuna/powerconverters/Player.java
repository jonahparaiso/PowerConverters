package com.bibfortuna.powerconverters;

import com.bibfortuna.powerconverters.Feats.*;
import com.bibfortuna.powerconverters.Classes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.HashMap;

public class Player {
    protected Dice die;//NOT THE VITALITY DIE, this is used to randomly determine data
    protected final String name;
    private final Species race;
    private final String gender;
    protected int level;
    private int forceLevel;
    private int exp;
    protected int credits;
    private Planet homeworld;

    protected HashMap<String, Classs> classes;
    private int classPenalty;
    private String firstClass;

    /**
     * For the purpose of leveling up and multiclassing, there needs to be a reference to the newest
     * class leveling up.
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
    protected int STR;//TODO check out RXJava and Observables/Observers
    private int sMod;
    private int strTempScore;
    private int strTempMod;
    private boolean isStrBuffed;

    protected int DEX;
    private int dMod;
    private int dexTempMod;
    private int dexTempScore;
    private boolean isDexBuffed;

    protected int CON;
    private int cMod;
    private int conTempScore;
    private int conTempMod;
    private boolean isConBuffed;

    protected int INT;
    private int iMod;
    private int intTempScore;
    private int intTempMod;
    private boolean isIntBuffed;

    protected int WIS;
    private int wMod;
    private int wisTempScore;
    private int wisTempMod;
    private boolean isWisBuffed;

    protected int CHA;
    private int chMod;
    private int chaTempScore;
    private int chaTempMod;
    private boolean isChaBuffed;


    /**
     * Saving Throws
     */
    private int fortitude;// = baseFortitude + cMod + fortitudeMiscMod;
    private int baseFortitude;
    private int fortitudeMiscMod;

    private int reflex;// = baseReflex + dmod + reflexMiscMod;
    private int baseReflex;
    private int reflexMiscMod;

    protected int will;// = baseWill + wMod + willMiscMod;
    private int baseWill;
    private int willMiscMod;

    /**
     * Combat
     */
    protected int bab;
    private int sizeMod;

    private int melee;// = bab + sMod + sizeMod + meleeMiscMod;
    private int meleeMiscMod;

    private int ranged;// = bab + dMod + sizeMod + meleeMiscMod;
    private int rangedMiscMod;

    protected int defense;// = 10 + classDefBonus + dMod + dexTempMod + sizeMod + defenseMiscBonus + dodgeBonus
    private int dodgeBonus;
    private int classDefBonus;
    private int defenseMiscBonus;
    //protected int defenseArmorCheck;

    private int speed;

    private int initiative;// = dMod + initiativeMiscBonus
    private int initiativeMiscBonus;

    /**
     * Health
     */
    private int vitalityRolls;
    private int maxVP;
    private int currentVP;
    private int maxWP;
    private int currentWP;
    private int vitalityDie;

    /**
     * Miscellaneous data fields
     */
    protected int reputation;
    private int forcePoints;
    private int darkSidePoints;

    //Arrays of skills, 39 regular skills and 20 force skills
    protected Skill[] skills;
    private Skill[] forceSkills;
    private int skillPointsToSpend;
    private int totalRanks;

    private LinkedList<Language> languages;

    protected LinkedList<Ability> abilities;

    //TODO Need to add the rest of the member variables and figure out how to do feats, items, etc.
    public HashMap<String,Feat> feats;
    private HashMap<String,Feat> featLibrary;


    /*TODO Weapons and Armor*/
    //protected LinkedList<Item> items;
    //protected LinkedList<Weapon> weapons;

    /**
     * The most random character constructor.
     */
    Player() {
        this.die = new Dice();
        int lvl = die.roll(20);
        boolean printing = die.roll(3) > 1;
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

        levelUp(c, lvl, printing, "LIST");

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
    public Player(String n, String sex, Species r, Classs c, int lvl, int str, int dex, int con, int iq, int wis, int cha, boolean printing, String printType) {
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
        applySpeciesBonus(r, printingCheck(printType, "applySpeciesBonus"));
        refreshMods();
        applyClasss(c, printingCheck(printType, "applyClasss"));
        levelUp(c, lvl, printing, printType);
        //testing all feats
        for (String key : featLibrary.keySet())
            addFeat(key, printingCheck(printType, "addFeat"));
        initializeFeats(printingCheck(printType, "initializeFeats"));
    }

    /**
     * Randomly generates information based on the class, species, and level. Useful for DM's for instant
     * NPC spawning.
     *
     * @param r   The species of the NPC.
     * @param c   The class of the NPC, their stats will be based on the class's favorite attributes.
     * @param lvl The lvl of the NPC.
     * @param printing Whether to print out statements for debugging or not.
     * @param printType The desired function to print out for debugging. See printingType() for possible values
     */
    Player(Species r, Classs c, int lvl, boolean printing, String printType) {
        printType = printType.toUpperCase();

        this.die = new Dice();
        switch (die.roll(2)) {
            case 1:
                gender = "male";
                break;
            default:
                gender = "female";
                break;
        }
        this.name = randomName(gender, printingCheck(printType, "randomName"));
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

        randomNewAttributes(c, printingCheck(printType, "randomNewAttributes"));
        initializeForcePoints(c, lvl);
        applySpeciesBonus(r, printingCheck(printType, "applySpeciesBonus"));
        refreshMods();
        applyClasss(c, printingCheck(printType, "applyClasss"));

        levelUp(c, lvl, printing, printType);

        //testing all feats
        for (String key : featLibrary.keySet())
            addFeat(key, printingCheck(printType, "addFeat"));
        initializeFeats(printingCheck(printType, "initializeFeats"));

        if (printingCheck(printType, "list")) {
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
     * Multiclass constructor that specifies two classes and the desired appropriate levels.
     *
     * @param c1 First classs
     * @param c1lvl Target level for the first classs
     * @param c2 Second classs
     * @param c2lvl Target level for the second classs
     * @param printing Boolean for whether or not to print out debugging statements
     * @param printType String specifying which method to print debugging statements
     */
    Player(Classs c1, int c1lvl, Classs c2, int c2lvl, boolean printing, String printType) {
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
        this.name = randomName(gender, printingCheck(printType, "randomName"));

        initializeForcePoints(c1, c1lvl);
        this.level = 0;
        this.exp = 0;

        randomNewAttributes(c1, printingCheck(printType, "randomNewAttributes"));
        applySpeciesBonus(race, printingCheck(printType, "applySpeciesBonus"));
        refreshMods();
        applyClasss(c1, printingCheck(printType, "applyClasss"));

        levelUp(c1, c1lvl, printing, printType);
        levelUp(c2, c2lvl, printing, printType);

        //testing all feats
        for (String key : featLibrary.keySet())
            addFeat(key, printingCheck(printType, "addFeat"));
        initializeFeats(printingCheck(printType, "initializeFeats"));
        if (printingCheck(printType, "feats"))
            printFeats();
    }

    /**
     * A helper method for the Player constructors. You may not necessarily want to see debugging print
     * statements for each method, so this returns the correct boolean based on a set given number of
     * functions to check for.
     * @param printType The String to compare to the set of examples.
     * @return true if the two inputs match, false otherwise
     */
    private boolean printingCheck(String printType, String functionName) {
        printType = printType.toUpperCase();
        if(printType.equals("ALL"))
            return true;
        functionName = functionName.toUpperCase();
        if (printType.contains("HELP") || printType.length() < 3 ) {
            System.out.println("Valid entries for printingCheck are:\n" +
                    "randomName, randomNewAttributes, applySpeciesBonus,lvlUpAttributes, lvlUpNewSkills,\n" +
                    "lvlUpVitality, updateExp, applyClasss, levelUp, initializeFeats, feats, list, all, none\n");
            return Boolean.parseBoolean(null);
        }
        try {
            return printType.equals(functionName);
        }
        catch (NullPointerException e) {
            System.out.println("Invalid entry for String functionName. Valid entries are:\n" +
                    "randomName, randomNewAttributes, initializeForcePoints, applySpeciesBonus,\n" +
                    "refreshMods, applyClasss, levelUp, initializeFeats, feats, list, all, none\n");
            return Boolean.parseBoolean(null);
        }
    }

    ///////////////////////////////////// Name/Class/Race RNG //////////////////////////////////////

    /**
     * Helper method to randomly select a name based on gender. Perhaps it could be improved to make
     * choices based on species, but that is much more complex, maybe I can do that later.
     *
     * @param sex Desired gender for the name.
     * @return The randomly generated first and last name.
     */
    private String randomName(String sex, boolean printing) {
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
            System.out.printf("Picked %s for a last name.%n", lastName);
        return firstName + " " + lastName;
    }

    /**
     * Outputs a random class based on if the player calling the function is an NPC or a real PC.
     *
     * @param isNPC Whether or not the function should include nonHero classes
     * @return The randomized class
     */
    private Classs randomClasss(boolean isNPC) {
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
     * Generates an unbiased race. True random.
     * @return The species randomly generated taken from the list of all species.
     */
    private Species randomRace() {
        int randomSpecies = die.roll(Species.values().length) - 1;
        return Species.values()[randomSpecies];
    }

    //////////////////////////////////////// Attribute RNG /////////////////////////////////////////

    /**
     * Automatically replicates the attribute generation for a first level hero. Normally, the player
     * will roll four d6 and take the sum of the three highest dice. This will range from 7 to 18
     * typically. This process happens six times. To prevent utterly terrible heroes, this method can
     * easily remedy the poor luck by taking the best 6 of 7 rolls.
     */
    private void randomNewAttributes(Classs c, boolean printing) {
        Dice die = new Dice();
        Integer[] temp = new Integer[7];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = die.bestOf(3, 4, 6);
        }
        Arrays.sort(temp, Collections.reverseOrder());

        //Depending on which stat is their favorite, give the appropriate (best or next best) roll to said attribute
        setStartingAttribute(0, temp, c.getFav1(), printing);
        setStartingAttribute(1, temp, c.getFav2(), printing);
        setStartingAttribute(2, temp, c.getFav3(), printing);
        setStartingAttribute(3, temp, c.getFav4(), printing);
        setStartingAttribute(4, temp, c.getFav5(), printing);
        setStartingAttribute(5, temp, c.getFav6(), printing);

        if (printing)
            System.out.printf("Starting stats for the new %s %s:%n%nSTR=%d%nDEX=%d%nCON=%d%nINT=%d%nWIS=%d%nCHA=%d%n%n", c.getName(), name, STR, DEX, CON, INT, WIS, CHA);
    }

    /**
     * Helper method to the randomNewAttributes method. Takes an index, an array, and an Attribute.
     * Sets the appropriate Attribute to the corresponding value in the array
     * @param i index of the target value in the passed in array
     * @param temp a sorted array of starting attribute values, an example: {17, 16, 15, 14, 12, 9}
     * @param fav the type of Attribute to initialize, example: Charisma, or CHA.
     */
    private void setStartingAttribute(int i, Integer[] temp, Attribute fav, boolean printing) {
        switch (fav) {
            case STR:
                this.STR = temp[i];
                break;
            case DEX:
                this.DEX = temp[i];
                break;
            case CON:
                this.CON = temp[i];
                break;
            case INT:
                this.INT = temp[i];
                break;
            case WIS:
                this.WIS = temp[i];
                break;
            case CHA:
                this.CHA = temp[i];
                break;
        }
        if (printing)
            System.out.printf("Set %s to %d%n", fav.name(), temp[i]);
    }

    /**
     * Smartly picks attributes to increment, prioritizing odd numbers (to get a modifier change). The
     * Attribute chosen is based on their class and favorite Attributes. If an Attribute is already at
     * 18, they will chose another Attribute to increment.
     *
     * @param c   the given class to base choices on
     * @param lvl the target level to level up to, ASSUMING THE PLAYER IS GOING UP FROM ZERO
     */
    private void lvlUpAttributes(Classs c, int lvl, boolean printing) {
        //Every 4th level a new attribute point is obtained
        int attributePointsLeft = 0;
        if (lvl % 4 == 0)
            attributePointsLeft = 1;
        if (printing)
            System.out.printf("Leveling up %s's attributes by %d points%n", this.name, attributePointsLeft);
        while (attributePointsLeft > 0) {

            //First even out all stats starting with their favorites, then start buffing their favorite stats
            attributePointsLeft = evenOutAttributes(c.getFav1(), printing);
            if (attributePointsLeft == 0)
                break;

            attributePointsLeft = evenOutAttributes(c.getFav2(), printing);
            if (attributePointsLeft == 0)
                break;

            attributePointsLeft = evenOutAttributes(c.getFav3(), printing);
            if (attributePointsLeft == 0)
                break;

            attributePointsLeft = evenOutAttributes(c.getFav4(), printing);
            if (attributePointsLeft == 0)
                break;

            attributePointsLeft = evenOutAttributes(c.getFav5(), printing);
            if (attributePointsLeft == 0)
                break;

            attributePointsLeft = evenOutAttributes(c.getFav6(), printing);
            if (attributePointsLeft == 0)
                break;

            //All stats are now even, so now get the favorite Attribute up to 18
            attributePointsLeft = attributeToTarget(c.getFav1(), printing, 18);
            if (attributePointsLeft == 0)
                break;

            //Get the second favorite attribute up to 16.
            attributePointsLeft = attributeToTarget(c.getFav2(), printing, 16);
            if (attributePointsLeft == 0)
                break;

            //Get the third favorite stat to 14.
            attributePointsLeft = attributeToTarget(c.getFav3(), printing, 14);
            if (attributePointsLeft == 0)
                break;

            //Get the fourth favorite stat to 14
            attributePointsLeft = attributeToTarget(c.getFav4(), printing, 14);
            if (attributePointsLeft == 0)
                break;

            //Get the fifth favorite stat to 12
            attributePointsLeft = attributeToTarget(c.getFav5(), printing, 12);
            if (attributePointsLeft == 0)
                break;

            //Get the sixth favorite stat to 10
            attributePointsLeft = attributeToTarget(c.getFav6(), printing, 10);
            if (attributePointsLeft == 0)
                break;

            /*If this is somehow reached, then the hero has fantastic starting stats, and it
             * means the stats are at least at 18, 16, 14, 14, 12, and 10. Now we can max out the best stat.
             * */
            attributePointsLeft = attributeToTarget(c.getFav1(), printing, 30);
            if (attributePointsLeft == 0)
                break;

            if (printing) {
                System.out.printf("Reached end of while loop%nSTR=%d%nDEX=%d%nCON=%d%nINT=%d%nWIS=%d%nCHA=%d%n%nPoints Left=%d%n", STR, DEX, CON, INT, WIS, CHA, attributePointsLeft);
            }
        }

        if (printing)
            System.out.printf("Finished leveling up %s's attributes.%n%n", this.name);
        refreshMods();
    }

    /**
     * Helper method for the above lvlUpAttribute function. Increments the appropriate Attribute based
     * on if it is odd. For example, CHA is passed and CHA's value is 13, the return of this function
     * would be 0 because CHA was incremented to 14.
     *
     * @param att The Attribute to increment
     * @param printing Debugging print statements
     * @return 0 if the Attribute was incremented, 1 if it was not incremented
     */
    private int evenOutAttributes(Attribute att, boolean printing) {
        switch (att) {
            case STR:
                if (this.STR % 2 != 0) {
                    this.STR++;
                    if (printing)
                        System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    return 0;
                }
                break;

            case DEX:
                if (this.DEX % 2 != 0) {
                    this.DEX++;
                    if (printing)
                        System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    return 0;
                }
                break;

            case CON:
                if (this.CON % 2 != 0) {
                    this.CON++;
                    if (printing)
                        System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    return 0;
                }
                break;

            case INT:
                if (this.INT % 2 != 0) {
                    this.INT++;
                    if (printing)
                        System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    return 0;
                }
                break;
            case WIS:
                if (this.WIS % 2 != 0) {
                    this.WIS++;
                    if (printing)
                        System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    return 0;
                }
                break;

            case CHA:
                if (this.CHA % 2 != 0) {
                    this.CHA++;
                    if (printing)
                        System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    return 0;
                }
                break;
        }
        //If this is reached, then the attribute was already even, so it wasn't incremented
        return 1;
    }

    /**
     * Helper method for the above lvlUpAttribute function. Increments the appropriate attribute based
     * on the attribute passed to it and a target integer goal to be reached. For example, STR is passed
     * along with 18, 1 would be returned if the STR had a value of 19 because it already met the target
     * goal, so it was not incremented.
     *
     * @param att The Attribute to increment
     * @param printing Debugging print statements
     * @param target The desired value for the passed in Attribute
     * @return 0 if the attribute was incremented, 1 if something was not incremented
     */
    private int attributeToTarget(Attribute att, boolean printing, int target) {
        switch (att) {
            case STR:
                if (this.STR < target) {
                    this.STR++;
                    if (printing)
                        System.out.printf("%s's STR went from %d to %d%n", this.name, STR - 1, STR);
                    return 0;
                }
                break;

            case DEX:
                if (this.DEX < target) {
                    this.DEX++;
                    if (printing)
                        System.out.printf("%s's DEX went from %d to %d%n", this.name, DEX - 1, DEX);
                    return 0;
                }
                break;

            case CON:
                if (this.CON < target) {
                    this.CON++;
                    if (printing)
                        System.out.printf("%s's CON went from %d to %d%n", this.name, CON - 1, CON);
                    return 0;
                }
                break;

            case INT:
                if (this.INT < target) {
                    this.INT++;
                    if (printing)
                        System.out.printf("%s's INT went from %d to %d%n", this.name, INT - 1, INT);
                    return 0;
                }
                break;

            case WIS:
                if (this.WIS < target) {
                    this.WIS++;
                    if (printing)
                        System.out.printf("%s's WIS went from %d to %d%n", this.name, WIS - 1, WIS);
                    return 0;
                }
                break;

            case CHA:
                if (this.CHA < target) {
                    this.CHA++;
                    if (printing)
                        System.out.printf("%s's CHA went from %d to %d%n", this.name, CHA - 1, CHA);
                    return 0;
                }
                break;
        }
        //If this is reached, then the attribute had already reached the target, so it wasn't incremented
        return 1;
    }

    ////////////////////////////////////////// Skill RNG ///////////////////////////////////////////

    /**
     * Allots a certain number of skill points to a hero based on their class and race. Humans get one
     * additional skill point per level, they're OP. I mean just look at the franchise. Kit Fisto got wrecked
     * while your boi Daddy Palpatine masterminded the whole galaxy.
     *
     * @param c The Classs determines how many skill points are allotted
     * @param printing Used for debugging, prints data to the console if true
     */
    private void lvlUpNewSkills(Classs c, boolean printing) {
        //At first level, heroes start off with four times the amount they gain normally when leveling up
        if (this.level == 1) {
            if (classes.get(c.name).getSkillUp() + iMod >= 1)
                skillPointsToSpend += ((classes.get(c.name).getSkillUp() + iMod) * 4);
            else
                skillPointsToSpend += 4;
            if (race == Species.HUMAN)
                skillPointsToSpend += 4;
            spendSkillPoints(c, printing);
        } else {
            if (classes.get(c.name).getSkillUp() + iMod >= 1)
                skillPointsToSpend += (classes.get(c.name).getSkillUp() + iMod);
            else
                skillPointsToSpend++;
            if (race == Species.HUMAN)
                skillPointsToSpend++;
            spendSkillPoints(c, printing);
        }
    }

    /**
     * Randomly picks one of the unit's class skills and spends a skill point IFF it is allowed to
     * TODO need to consider force feats.
     *
     * @param c The class to level up.
     */
    private void spendSkillPoints(Classs c, boolean printing) {
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
                    if (skills[classSkills.get(skillToIncrement)].getRank() < max) {
                        skills[classSkills.get(skillToIncrement)].addRanks(1, printing);
                        totalRanks++;
                    }
                    //If you reach here, then the skill it rolled for is already maxed out, so pick a new non class skill
                    else {
                        if (tryAgain < tryTimes) {
                            if (printing)
                                System.out.printf("Trying again. %s is maxed out%n %d more tries.%n",skills[classSkills.get(skillToIncrement)].getType().getName(), tryTimes-tryAgain);
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
                if (skills[classSkills.get(skillToIncrement)].getRank() < max) {
                    skills[classSkills.get(skillToIncrement)].addRanks(1, printing);
                    totalRanks++;
                }
                //If you reach here, then the skill it rolled for is already maxed out, so pick a new non class skill
                else {
                    if (tryAgain < tryTimes) {
                        if (printing)
                            System.out.printf("Trying again. %s is maxed out%n %d more tries.%n",skills[classSkills.get(skillToIncrement)].getType().getName(), tryTimes-tryAgain);
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

    /////////////////////////////////// Leveling Up RNG ////////////////////////////////////////////

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
    private void levelUp(Classs c, int levels, boolean printing, String printType) {
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
            if (printing && printType.contains("lvl"))
                System.out.printf("%n%nLeveling up %s from %d to %d in the %s class%nTotal level: %d%n", name, c.classLvl, currentLvl, c.getName(), level);
            lvlUpAttributes(c, level, printingCheck(printType, "lvlUpAttributes"));
            lvlUpNewSkills(c, printingCheck(printType, "lvlUpNewSkills"));
            refreshBaseValues(c, currentLvl, printingCheck(printType, "refreshBaseValues"));
            safe = level %2 != 0;//if level is odd, go safe, otherwise roll a die.
            lvlUpVitality(c, safe, printingCheck(printType, "lvlUpVitality"));
            clearBuffs();
            refreshCombat();
            refreshInitiative();
            refreshDefense();
            refreshMods();
            refreshSaves();
            updateForcePoints();
            classes.get(c.name).classLvlUp();
            updateExp(level, printingCheck(printType, "updateExp"));
            //TODO NEED TO DO FEATS AND ABILITIES/POWERS
            //A hero gets a feat every three levels regardless of class
            if (level%3 == 0 || level == 1) {
                //Randomly grab one of their class's favorite feats.
                System.out.println("TODO need to add a feat here");
            }

            if (printingCheck(printType, "list")) {
                System.out.printf("%nFinished leveling up %s to %d in the %s class%n%nTotal level: %d%n", name, currentLvl, c.getName(), level);
                printAttributes();
                printSavingThrows();
                printSkills();
                printMisc();
                printHealth();
                printCombat();
            }
            if (printingCheck(printType, "feats"))
                printFeats();
            levels--;
        }
    }

    /**
     * Updates the experience points based on level.
     * @param lvl The level to update to
     * @param printing Whether to print or not for debugging purposes
     */
    private void updateExp(int lvl, boolean printing) {
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

    /**
     * Used while leveling up, this method works for both Force users and non users.
     */
    private void updateForcePoints() {
        if (forceLevel > 0 || forcePoints < 4)
            forcePoints++;
    }

    ////////////////////////////////// YOCO: You only call once ////////////////////////////////////

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

    /////////////////////////////////////// Refreshers /////////////////////////////////////////////

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
     * Sets all the modifiers in the skill arrays.
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

        /*
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
    private void refreshInitiative() { initiative = dMod + dexTempMod + initiativeMiscBonus; }

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
        //Improved Critical takes specialty parameter in the form of a String and Weapon Expertise Feat
        featLibrary.put("IMPROVED CRITICAL", new ImprovedCritical("Not Set", new Feat("BLANK", "One empty boi.")));
        featLibrary.put("IMPROVED DISARM", new ImprovedDisarm());
        featLibrary.put("IMPROVED FORCE MIND", new ImprovedForceMind());
        featLibrary.put("IMPROVED INITIATIVE", new ImprovedInitiative());
        featLibrary.put("IMPROVED MARTIAL ARTS", new ImprovedMartialArts());
        featLibrary.put("IMPROVED TRIP", new ImprovedTrip());
        featLibrary.put("IMPROVED TWO WEAPON FIGHTING", new ImprovedTwoWeaponFighting());
        featLibrary.put("INFAMY", new Infamy());
        featLibrary.put("INFLUENCE", new Influence());
        featLibrary.put("IRON WILL", new IronWill());
        featLibrary.put("KNIGHT DEFENSE", new KnightDefense());
        featLibrary.put("KNIGHT MIND", new KnightMind());
        featLibrary.put("KNIGHT SPEED", new KnightSpeed());
        featLibrary.put("LIGHTNING REFLEXES", new LightningReflexes());
        featLibrary.put("LIGHTSABER DEFENSE", new LightsaberDefense());
        featLibrary.put("LINK", new Link());
        featLibrary.put("LOW PROFILE", new LowProfile());
        featLibrary.put("MALEVOLENT", new Malevolent());
        featLibrary.put("MARTIAL ARTS", new MartialArts());
        featLibrary.put("MASTER DEFENSE", new MasterDefense());
        featLibrary.put("MASTER MIND", new MasterMind());
        featLibrary.put("MASTER SPEED", new MasterSpeed());
        featLibrary.put("METTLE", new Mettle());
        featLibrary.put("MIMIC", new Mimic());
        featLibrary.put("MIND TRICK", new MindTrick());
        featLibrary.put("MOBILITY", new Mobility());
        featLibrary.put("MULTISHOT", new Multishot());
        featLibrary.put("NIMBLE", new Nimble());
        featLibrary.put("PERSUASIVE", new Persuasive());
        featLibrary.put("POINT BLANK SHOT", new PointBlankShot());
        featLibrary.put("POWER ATTACK", new PowerAttack());
        featLibrary.put("PRECISE SHOT", new PreciseShot());
        featLibrary.put("QUICK DRAW", new QuickDraw());
        featLibrary.put("QUICKNESS", new Quickness());
        featLibrary.put("RAGE", new Rage());
        featLibrary.put("RAPID SHOT", new RapidShot());
        featLibrary.put("RUGGED", new Rugged());
        featLibrary.put("RUN", new Run());
        featLibrary.put("SHARP EYED", new SharpEyed());
        featLibrary.put("SHOT ON THE RUN", new ShotOnTheRun());
        featLibrary.put("SHILL EMPHASIS", new SkillEmphasis());
        featLibrary.put("SPACER", new Spacer());
        featLibrary.put("SPRING ATTACK", new SpringAttack());
        featLibrary.put("STAMINA", new Stamina());
        featLibrary.put("STARSHIP DODGE", new StarshipDodge());
        featLibrary.put("STARSHIP OPERATION", new StarshipOperation());
        featLibrary.put("STEADY", new Steady());
        featLibrary.put("STEALTHY", new Steathly());
        featLibrary.put("SUNDER", new Sunder());
        featLibrary.put("SURGERY", new Surgery());
        featLibrary.put("TOUGHNESS", new Toughness());
        featLibrary.put("TRACK", new Track());
        featLibrary.put("TRICK", new Trick());
        featLibrary.put("TRUSTWORTHY", new Trustworthy());
        featLibrary.put("TWO WEAPON FIGHTING", new TwoWeaponFighting());
        featLibrary.put("WEAPON FINESSE", new WeaponFinesse());
        featLibrary.put("WEAPON FOCUS", new WeaponFocus());
        featLibrary.put("WEAPON GROUP PROFICIENCY", new WeaponGroupProficiency());
        featLibrary.put("WHIRLWIND ATTACK", new WhirlwindAttack());
        featLibrary.put("ZERO G TRAINING", new ZeroGTraining());

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
        for (String key : this.feats.keySet()) {
            //if the feat has already been initialized, then continue to the next feat
            if (this.feats.get(key).isInitialized())
                continue;
            //check to see if the feat boosts skills
            if (this.feats.get(key).areSkillsBoosted()) {
                for (int i = 0; i < this.feats.get(key).getSkills().length; i++) {
                    //Loop through the array held by the Feat, inside there is information on which skills are boosted.
                    //Go through and add the bonuses to the corresponding misc bonus in the player skill array.
                    //TODO Can be optimized. Doesnt need full array to hold values, can just be a stack/queue/list of pairs of values
                    if (printing && this.feats.get(key).getSkills()[i] > 0)
                        System.out.printf("Adding %d misc bonus to %s%n",feats.get(key).getSkills()[i], skills[i].getType().getName());
                    skills[i].addMiscMod(this.feats.get(key).getSkills()[i]);
                }
            }
            //check to see if the Feat boosts Force skills
            else if (this.feats.get(key).areFSkillsBoosted()) {
                for (int i = 0; i < this.feats.get(key).getForceSkills().length; i++) {
                    //Loop through the array held by the Feat, inside there is information on which skills are boosted.
                    //Go through and add the bonuses to the corresponding misc bonus in the player skill array.
                    //TODO Can be optimized. Doesnt need full array to hold values, can just be a stack/queue/list of pairs of values
                    if (printing && this.feats.get(key).getForceSkills()[i] > 0)
                        System.out.printf("Adding %d misc bonus to %s%n",this.feats.get(key).getForceSkills()[i], forceSkills[i].getType().getName());
                    forceSkills[i].addMiscMod(this.feats.get(key).getForceSkills()[i]);
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
            if (this.feats.get(key).getMiscBonus() > 0)
                switch (key) {
                    case "DODGE":
                        dodgeBonus += this.feats.get(key).getMiscBonus();
                        break;
                    case "GREAT FORTITUDE":
                        fortitudeMiscMod += this.feats.get(key).getMiscBonus();
                    case "HEADSTRONG":
                        willMiscMod += this.feats.get(key).getMiscBonus();
                    case "IMPROVED CRITICAL":
                        //TODO need to do when I do weapons, probably something like this:
                        //weaponRack(feats.get(key).getSpecialty()).increaseCritical(feats.get(key).getMiscBonus());
                        break;
                    case "IMPROVED INITIATIVE":
                        initiativeMiscBonus += this.feats.get(key).getMiscBonus();
                        break;
                    case "FAME":
                        reputation += this.feats.get(key).getMiscBonus();
                        break;
                    case "INFAMY":
                        reputation += this.feats.get(key).getMiscBonus();
                        break;
                    case "KNIGHT DEFENSE":
                        dodgeBonus += this.feats.get(key).getMiscBonus();
                        break;
                    case "IRON WILL":
                        willMiscMod += this.feats.get(key).getMiscBonus();
                        break;
                    case "LIGHTNING REFLEXES":
                        reflexMiscMod += this.feats.get(key).getMiscBonus();
                        break;
                    case "LIGHTSABER DEFENSES":
                        dodgeBonus += this.feats.get(key).getMiscBonus();
                        break;
                    case "LOW PROFILE":
                        reputation += this.feats.get(key).getMiscBonus();
                        break;
                    case "MASTER DEFENSE":
                        dodgeBonus += this.feats.get(key).getMiscBonus();
                        break;
                    case "QUICKNESS":
                        maxVP += this.feats.get(key).getMiscBonus();
                        break;
                }

                this.feats.get(key).initialize();

            if (printing)
                System.out.printf("%s : %s%n", key, this.feats.get(key).getInfo());
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
            if (key.length() == 3 && !key.equals("RUN")) {
                switch (key) {
                    case "STR":
                        if (STR >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient ").append(key).append(". Required: ").append(reqValue).append(" Has ").append(STR).append("\n");
                            out = false;
                        }
                        break;

                    case "DEX":
                        if (DEX >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient ").append(key).append(". Required: ").append(reqValue).append(" Has ").append(DEX).append("\n");
                            out = false;
                        }
                        break;

                    case "CON":
                        if (CON >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient ").append(key).append(". Required: ").append(reqValue).append(" Has ").append(CON).append("\n");
                            out = false;
                        }
                        break;

                    case "INT":
                        if (INT >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient ").append(key).append(". Required: ").append(reqValue).append(" Has ").append(INT).append("\n");
                            out = false;
                        }
                        break;

                    case "WIS":
                        if (WIS >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient ").append(key).append(". Required: ").append(reqValue).append(" Has ").append(WIS).append("\n");
                            out = false;
                        }
                        break;

                    case "CHA":
                        if (CHA >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient ").append(key).append(". Required: ").append(reqValue).append(" Has ").append(CHA).append("\n");
                            out = false;
                        }
                        break;

                    case "BAB":
                        if (bab >= reqValue)
                            out = true;
                        else {
                            requirements.append("   Insufficient ").append(key).append(". Required: ").append(reqValue).append(" Has ").append(bab).append("\n");
                            out = false;
                        }
                        break;
                }
            }
            //Check to see if the key is a number, indicating an index of a skill, which means that the
            //value of the key (integer value) represents the required ranks in said skill
            else try {
                skillIndex = Integer.parseInt(key);
                //check to see if the skill is a regular or force skill. Below means it's a regular skill
                if (skillIndex < skills.length) {
                    if (skills[skillIndex].getRank() >= reqValue)
                        out = true;
                    else {
                        requirements.append("   Insufficient ").append(skills[skillIndex].getType().getName()).append(". Required: ").append(reqValue).append(" Has ").append((int) skills[skillIndex].getRank()).append("\n");
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
                        requirements.append("   Insufficient ").append(forceSkills[skillIndex].getType().getName()).append(". Required: ").append(reqValue).append(" Has ").append((int) forceSkills[skillIndex].getRank()).append("\n");
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
                        requirements.append("   Insufficient force level. Required: ").append(reqValue).append(" Has: ").append(forceLevel).append("\n");
                        continue;
                    }
                }
                if (((this.feats.containsKey(key)) && (map.get(key) == 1)) || ((!this.feats.containsKey(key)) && (map.get(key) == 0))) {
                    out = true;
                }
                else {
                    out = false;
                    requirements.append("   ").append(key).append("\n");
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
            this.feats.put(s,f);
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
    protected void printSkills() {
        System.out.printf("Skill Modifiers for %s are as such:%n", name);
        System.out.println("Skill Name---------------AtMod-Ranks-Misc-Total Skill Modifier");
        StringBuilder preBuff;
        String attBuff;
        String doubleDigits;
        for (Skill temp : skills) {
            preBuff = new StringBuilder(temp.getType().getName());
            while (preBuff.length() < 19)
                preBuff.append(" ");
            if (temp.getAttributeModifier() < 0) attBuff = "";
            else attBuff = " ";
            if (temp.getRank() > 9) doubleDigits = "";
            else doubleDigits = " ";
            System.out.printf("%s(%s): %d%s   %.0f%s   %d    %d%n", preBuff.toString(), temp.getType().getAttribute(), temp.getAttributeModifier(), attBuff, temp.getRank(), doubleDigits, temp.getMisc(), temp.getTotalModifier());
        }
        if (forceLevel > 0) {
            System.out.printf("%nForce Class Skills for %s are as such:%n", name);
            for (Skill temp : forceSkills) {
                preBuff = new StringBuilder(temp.getType().getName());
                while (preBuff.length() < 19)
                    preBuff.append(" ");
                if (temp.getAttributeModifier() < 0) attBuff = "";
                else attBuff = " ";
                if (temp.getRank() > 9) doubleDigits = "";
                else doubleDigits = " ";
                System.out.printf("%s(%s): %d%s   %.0f%s   %d    %d%n", preBuff.toString(), temp.getType().getAttribute(), temp.getAttributeModifier(), attBuff, temp.getRank(), doubleDigits, temp.getMisc(), temp.getTotalModifier());
            }
        }
        System.out.printf("Total Ranks = %d%n",totalRanks);
    }

    /**
     * Print Attributes as they would appear on the character sheet
     */
    protected void printAttributes() {
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
    protected void printSavingThrows() {
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

    protected void printMisc() {
        StringBuilder listOfClasses = new StringBuilder();
        for (String key : classes.keySet())
            listOfClasses.append(String.format(key + " %d ", classes.get(key).classLvl));
        System.out.printf("%nCLASS(ES)  = %s%n" +
                            "EXP        = %d%n" +
                            "CREDITS    = %d%n" +
                            "LEVEL      = %d%n" +
                            "FORCE LEVEL= %d%n" +
                            "GENDER     = %s%n" +
                            "SPECIES    = %s%n", listOfClasses.toString(), exp, credits, level, forceLevel, gender, race.name());
    }

    protected void printHealth() {
        System.out.printf("%nMAX VITALITY = %d%nCURRENT VP   = %d%nWOUND POINTS = %d%n", maxVP, currentVP, currentWP);
    }

    protected void printCombat() {
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

    protected void printFeats() {
        System.out.printf("FEATS at lvl %d%n",this.level);
        for (String key : this.feats.keySet()) {
            if (this.feats.get(key).getSpecialty().length() != 0)
                System.out.printf("%s (%s)%n", key, this.feats.get(key).getSpecialty());
            else System.out.println(key);
        }
    }
}
