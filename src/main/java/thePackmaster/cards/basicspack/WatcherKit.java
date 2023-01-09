package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.cards.purple.Eruption;
import com.megacrit.cardcrawl.cards.purple.Vigilance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WatcherKit extends AbstractKitCard{
    public final static String ID = makeID("WatcherKit");

    public WatcherKit() {
        super(ID, new Eruption(), new Vigilance());
    }
}
