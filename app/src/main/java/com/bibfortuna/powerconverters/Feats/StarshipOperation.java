package com.bibfortuna.powerconverters.Feats;

public class StarshipOperation extends Feat {
    public StarshipOperation(int fighter_1_Or_Transport_2){
        super("STARSHIP OPERATION", "Select a class or starship (starfighter, space transport, or capital\n" +
                "\tship). You are proficient at operating that class of starship.\n" +
                "\tPrerequisite: Pilot 2 ranks\n" +
                "\tBenefit: You take no penalty on Pilot checks or attack rolls made when operating a starship of the selected class.\n" +
                "\tNormal: Characters without this feat take a -4 penalty on Pilot checks made to operate a starship\n" +
                "\t\tand on attacks made with starship weapons.\n" +
                "\tSpecial: You can gain this feat multiple times. Each time you take the feat, it applies to a different class of starship.");
        prereqs.put("27",2);
    }
}
