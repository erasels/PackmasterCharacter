package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.cards.green.Survivor;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SilentKit extends AbstractKitCard{
    public final static String ID = makeID("SilentKit");

    public SilentKit() {
        super(ID, new Neutralize(), new Survivor());
    }
}
