package com.bibfortuna.powerconverters;

public enum FirstName {
    Thraken("m"), Moth("f"), David("m"), Growook_Nik("n"), Par("f"), Gentti("n"), Trinna("f"), Ettal("n"),
    Surlr("n"), Valin("n"), Graeme("n"), Xero("n"), Kor("n"), Hal("m"), Xana_Thel("n"), Daylana("f"),
    Bobi("n"), Kel("n"), Benj_ew("n"), Kazic("n"), Wug("n"), Kole("n"), Hopp("n"), Jauhzmynn("n"),
    Rasha("n"), Groznik("m"), Aath("n"), Quin("n"), Argon("m"), Siot("n"), Delmi("n"), Garen("n"),
    Vincenzo("m"), Shram("n"), Je_Gelt("n"), Xuenti("n"), Kalluk_Oras("n"), Karei("m"), Zoras("n"),
    Sarli("n"), Stromgald("m"), Tru_Lek("n"), Simo("n"), Ethea("n"), Dajo("n"), Jenica("f"), Dewson("m"),
    Victor("m"), Fanus("n"), Nomi("f"), Dian("n"), Archlonus("m"), Az_mo("n"), Zungher("m"), Jazen("m"),
    Kylain("f"), Tendra("n"), Isamu("n"), Yu_Sien("n"), Mugami("n"), Icarus("m"), Jaxon("m"), Dax("n"),
    Shesa("f"), Vyri("n"), Evvin("n"), Towan("n"), Bando("m"), Akiva("f"), Duval("n"), Cregan("m"),
    Wagg("n"), Shanna("f"), Xyyyr("m"), Alix("n"), Qore("n"), Angus("m"), Alys("n"), Jax("m"), Bezor("m"),
    Grooda("n"), Illek("n"), Triko("n"), Krenis("n"), Jaleel("n"), Ganice("n"), Megara("f"), Maki("n"),
    Zyya_cah("n"), Tarken("m"), Varn("n"), Darius("m"), Eboi("n"), Bot("n"), Carga("n"), Voth("m"), Hakk("m"),
    Nelia("f"), Zhar_Khan("m"), Rogon("m"), Aerith("n"), Naomi("f"), Yan("f"), Pierso("f"), Moshrash("f"),
    Pyasdish("f"), Kefra("f"), Yinedh("f"), Zifkirne("f"), Udrirsa("f"), Rolftasta("f"), Cerdilta("f"),
    Zirirsi("f"), Tark("m"), Bli("m"), Thit("m"), Konga("m"), Woortul("m"), Thirbunz("m"), Pooshgrud("m"),
    Hung_ogor("m"), Groollpalit("m"), Blirboga("m"), Felvis("n"), Rurku("n"), Rollyeth("n"), Alnu("n"),
    Zeidhor("n"), Zinlom("n"), Vyel_Myem("n"), Delvu("n"), Ilmi("n"), Mamroth("n"), Finnoth("n"), Raflin("n"),
    Ned_heth("n"), Vimkaj("n"), Kiedhen("n"), Danluth("n"), Anem("n"), Osij("n"), Musa("n"), Sinveem("n")
    ;

    private String gender;
    private String name;

    FirstName(String sex) {
        if (sex.contains("m")) gender = "male";
        else if (sex.contains("f")) gender = "female";
        else gender = "neutral";

        name = this.name();
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
}
