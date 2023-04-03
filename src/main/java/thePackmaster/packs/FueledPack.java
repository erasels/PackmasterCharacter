package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.fueledpack.*;

import java.util.ArrayList;

public class FueledPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(FueledPack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public FueledPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(HotAsh.ID);
        cards.add(BurningSword.ID);
        cards.add(IridiumShield.ID);
        cards.add(FlameBlastMkTwo.ID);
        cards.add(Napalm.ID);
        cards.add(InfernoMkTwo.ID);
        cards.add(FlamingLeap.ID);
        cards.add(PoisonousSmoke.ID);
        cards.add(PhoenixHeart.ID);
        cards.add(BurnBright.ID);
        cards.add(ControlledBurn.ID);
        return cards;
    }
}
