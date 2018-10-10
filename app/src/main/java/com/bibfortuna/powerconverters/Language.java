package com.bibfortuna.powerconverters;

public enum Language {
    /*TODO
    Need to populate a bunch of languages.
     */
    BASIC(Planet.UTILITY),
    BINARY(Planet.UTILITY),
    BOCCE(Planet.UTILITY),
    BOTHESE(Planet.BOTHAWUI),
    CEREAN(Planet.CEREA),
    CHEUNH(Planet.CSILLA),
    DOSH(Planet.TRANDOSHA),
    DROIDSPEAK(Planet.UTILITY),
    DUG(Planet.MALASTARE),
    DURESE(Planet.DURO),
    EWOKESE(Planet.ENDOR),
    GAMORRESE(Planet.GAMORR),
    GEONOSIAN(Planet.GEONOSIS),
    GUNGAN(Planet.NABOO),
    HAPAN(Planet.HAPES_CONSORTIUM),
    HUTTESE(Planet.VARL),
    ITHORESE(Planet.ITHOR),
    JAWAESE(Planet.TATOOINE),
    KALEESH(Planet.KALEE),
    KAMINOAN(Planet.KAMINO),
    KEL_DOR(Planet.DORIN),
    LEKKU(Planet.RYLOTH),
    MANDO_A(Planet.MANDALORE),
    MON_CALAMARIAN(Planet.MON_CALAMARI),
    OLYS_CORELLISI(Planet.CORELLIA),
    PAK_PAK(Planet.NEIMOIDIA),
    QUARRENESE(Planet.MON_CALAMARI),
    RODESE(Planet.RODIA),
    RYL(Planet.RYLOTH),
    SHYRIIWOOK(Planet.KASHYYYK),
    SNIVVIAN(Planet.CADOMAI),
    SULLUSTESE(Planet.SULLUST),
    SITH(Planet.KORRIBAN),
    TOGRUTI(Planet.SHILI),
    TWI_LEKI(Planet.RYLOTH),
    TUSKEN(Planet.TATOOINE),
    ZABRAK(Planet.IRIDONIA)
    ;

    private Planet homeworld;

    Language(Planet h)
    {
        this.homeworld = h;
    }
}
