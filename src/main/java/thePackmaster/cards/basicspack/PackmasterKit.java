package thePackmaster.cards.basicspack;


import thePackmaster.cards.Cardistry;
import thePackmaster.cards.Rummage;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PackmasterKit extends AbstractKitCard{
    public final static String ID = makeID("PackmasterKit");

    public PackmasterKit() { super(ID, new Rummage(), new Cardistry(), CardRarity.UNCOMMON); }
}
