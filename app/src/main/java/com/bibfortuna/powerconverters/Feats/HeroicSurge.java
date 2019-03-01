package com.bibfortuna.powerconverters.Feats;

import com.bibfortuna.powerconverters.CheckTable;

public class HeroicSurge extends Feat {
    public HeroicSurge() {
        super("Heroic Surge", "You can perform an additional action in a round.\n" +
                "   Benefit: You may take an extra move action or attack action, either before or after\n" +
                "       your regular actions. You may use Heroic Surge a number of times per day depending\n" +
                "       on your character level (as shown below), but never more than once per round.\n" +
                "       Check the table with your character level.");

        setIsActivatedAbility(true);
        table = new CheckTable(5, 9, 13, 17,
                "1 surge per day", "2 surges per day", "3 surges per day", "4 surges per day", "5 surges per day",
                0, 0, 0, 0, 0);
    }
}
