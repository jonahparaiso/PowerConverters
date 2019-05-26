package com.bibfortuna.powerconverters.Feats;

import com.bibfortuna.powerconverters.CheckTable;

public class Track extends Feat {
    public Track() {
        super("TRACK", "You can follow the trails of creatures and characters across most types of terrain.\n" +
                "\tBenefit: To find tracks or to follow them for 1 kilometer requires a Survival check every time the\n" +
                "\t\ttracks become difficult to follow, such as when other tracks cross them or when the tracks backtrack and diverge.\n" +
                "\n" +
                "\t\tYou move at half your normal speed (or at your normal speed with a –5 penalty on the check). The DC\n" +
                "\t\tdepends on the surface and the prevailing conditions.\n\n" +
                "\tGROUND TYPES"+
                "\tVery Soft Ground: Any surface (fresh snow, thick dust, wet mud) that holds deep, clear impressions of footprints.\n" +
                "\tSoft Ground: Any surface soft enough to yield to pressure, but firmer than wet mud or fresh snow,\n" +
                "\t\tin which the quarry leaves frequent but shallow footprints.\n" +
                "\tFirm Ground: Most normal outdoor surfaces (such as lawns, fields, woods, and the like) or exceptionally\n" +
                "\t\tsoft or dirty indoor surfaces (thick rugs, very dirty or dusty floors). The quarry might leave some\n" +
                "\t\ttraces of its passage (broken branches, tufts of hair) but only occasional or partial footprints can be found.\n" +
                "\tHard Ground: Any surface that doesn’t hold footprints at all, such as bare rock, concrete, metal deckings,\n" +
                "\t\tor indoor floors. The quarry leaves only traces, such as scuff marks. If you fail a Survival check, you\n" +
                "\t\tcan retry after 1 hour (outdoors) or 10 minutes (indoors) of searching.\n\n"+
                "\tCONDITION MODIFIERS\n"
                );
    }
}
