package com.bibfortuna.powerconverters.Feats;

public class StarshipDodge extends Feat {
    public StarshipDodge(int fighter_1_Or_Transport_2) {
        super("STARSHIP DODGE", "Select a class of starship (starfighter or space transport). You are\n" +
                "\tadept at dodging attacks while piloting that class of starship.\n" +
                "\tPrerequisite: Dexterity 13, Pilot 6 ranks, Starship Operation (Starfighter) or Starship Operation (Space Transport)\n" +
                "\tBenefit: When piloting a starfighter or a space transport (depending on which class of starship\n" +
                "\t\tyou selected), during your action you designate an opposing starship and receive a+1 dodge bonus\n" +
                "\t\tto Defense against attacks from that opponent. You can select a new opponent on any action.\n" +
                "\tSpecial: You can gain this feat twice. Each time you take this feat, it applies to a different class of starship.");
        prereqs.put("DEX",13);
        prereqs.put("27",6);
        prereqs.put("STARSHIP OPERATION", fighter_1_Or_Transport_2);
    }
}
