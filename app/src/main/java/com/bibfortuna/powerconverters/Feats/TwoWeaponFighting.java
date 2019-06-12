package com.bibfortuna.powerconverters.Feats;

public class TwoWeaponFighting extends Feat {
    public TwoWeaponFighting() {
        super("TWO WEAPON FIGHTING", "You can fight with a weapon in each hand. You can make one extra attack each\n" +
                "\tround with the second weapon. Both weapons must make the same kind of attacks, either both ranged\n" +
                "\tattacks or both melee attacks.\n" +
                "\tBenefit: Your penalties for fighting with two weapons are lessened by 2.\n" +
                "\tNormal: See Attacking with Two Weapons Penalties.\n" +
                "\tSpecial: The Ambidexterity feat lessens the attack penalty for the second weapon by 4.");
        //TODO Implement a two weapon attack bonus field for the player class (starts as -6/-10?), then each
        //two weapon related feat affects that penalty
    }
}
