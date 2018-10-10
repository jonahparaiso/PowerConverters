package com.bibfortuna.powerconverters;

public enum Skilltype {
    //Regular Skills
    APPRAISE(Attribute.INT, 0, "Appraise"),
    ASTROGATE(Attribute.INT, 1, "Astrogate"),
    BALANCE(Attribute.DEX, 2, "Balance"),
    BLUFF(Attribute.CHA, 3, "Bluff"),
    CLIMB(Attribute.STR, 4, "Climb"),
    COMPUTER(Attribute.INT, 5, "Computer Use"),
    CRAFT1(Attribute.INT, 6, "Craft1"),
    CRAFT2(Attribute.INT, 7, "Craft2"),
    CRAFT3(Attribute.INT, 8, "Craft3"),
    DEMOLITIONS(Attribute.INT, 9, "Demolitions"),
    DIPLOMACY(Attribute.CHA, 10, "Diplomacy"),
    DISABLE(Attribute.INT, 11, "Disable Device"),
    DISGUISE(Attribute.CHA, 12, "Disguise"),
    ENTERTAIN(Attribute.CHA, 13, "Entertain"),
    ESCAPE(Attribute.DEX, 14, "Escape"),
    FORGERY(Attribute.INT, 15, "Forgery"),
    GAMBLE(Attribute.WIS, 16, "Gamble"),
    GATHER(Attribute.CHA, 17, "Gather Information"),
    HANDLE(Attribute.CHA, 18, "Handle Animal"),
    HIDE(Attribute.DEX, 19, "Hide"),
    INTIMIDATE(Attribute.CHA, 20, "Intimidate"),
    JUMP(Attribute.STR, 21, "Jump"),
    KNOWLEDGE1(Attribute.INT, 22, "Knowledge1"),
    KNOWLEDGE2(Attribute.INT, 23, "Knowledge2"),
    KNOWLEDGE3(Attribute.INT, 24, "Knowledge3"),
    LISTEN(Attribute.WIS, 25, "Listen"),
    SNEAK(Attribute.DEX, 26, "Move Silently"),
    PILOT(Attribute.DEX, 27, "Pilot"),
    PROFESSION(Attribute.WIS, 28, "Profession"),
    REPAIR(Attribute.INT, 29, "Repair"),
    RIDE(Attribute.DEX, 30, "Ride"),
    SEARCH(Attribute.INT, 31, "Search"),
    SENSEMOTIVE(Attribute.WIS, 32, "Sense Motive"),
    SLEIGHT(Attribute.DEX, 33, "Sleight of Hand"),
    SPOT(Attribute.WIS, 34, "Spot"),
    SURVIVAL(Attribute.WIS, 35, "Survival"),
    SWIM(Attribute.STR, 36, "Swim"),
    TREAT(Attribute.WIS, 37, "Treat Injury"),
    TUMBLE(Attribute.DEX, 38, "Tumble"),

    //Force Skills;
    AFFECTMIND(Attribute.CHA, 0, "Affect Mind"),//39
    BATTLEMIND(Attribute.CON, 1, "Battlemind"),//40
    DRAINENERGY(Attribute.CON, 2, "Drain Energy"),//41
    EMPATHY(Attribute.WIS, 3, "Empathy"),//42
    ENHANCEABILITY(Attribute.CON, 4, "Enhance Ability"),//43
    ENHANCESENSES(Attribute.WIS, 5, "Enhance Senses"),//44
    FARSEEING(Attribute.WIS, 6, "Farseeing"),//45
    FEAR(Attribute.WIS, 7, "Fear"),//46
    FDEFENSE(Attribute.CHA, 8, "Force Defence"),//47
    FGRIP(Attribute.INT, 9, "Force Grip"),//48
    FLIGHTNING(Attribute.INT, 10, "Force Lightning"),//49
    FSTEALTH(Attribute.CHA, 11, "Force Stealth"),//50
    FSTRIKE(Attribute.INT, 12, "Force Strike"),//51
    FREINDSHIP(Attribute.CHA, 13, "Friendship"),//52
    HEALANOTHER(Attribute.WIS, 14, "Heal Another"),//53
    HEALSELF(Attribute.CHA, 15, "Heal Self"),//54
    ILLUSION(Attribute.CHA, 16, "Illusion"),//55
    MOVEOBJECT(Attribute.INT, 17, "Move Object"),//56
    SEEFORCE(Attribute.WIS, 18, "See Force"),//57
    TELEPATHY(Attribute.WIS, 19, "Telepathy");//58

    private Attribute type;
    private int i;
    private String name;

    Skilltype(Attribute newType, int index, String s) {
        this.type = newType;
        this.i = index;
        this.name = s;
    }

    public Attribute getAttribute() { return type; }
    public int getI() { return this.i; }
    public String getName() { return this.name; }
}
