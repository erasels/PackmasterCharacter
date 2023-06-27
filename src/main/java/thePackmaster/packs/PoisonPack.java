package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.poisonpack.VenomDip;

import java.util.ArrayList;

public class PoisonPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PoisonPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public PoisonPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(5, 1, 2, 2, 5, "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(PoisonedStab.ID);
        cards.add(DeadlyPoison.ID);
        cards.add(Catalyst.ID);
        cards.add(VenomDip.ID);
        cards.add(NoxiousFumes.ID);
        cards.add(BouncingFlask.ID);
        cards.add(CripplingPoison.ID);
        cards.add(Malaise.ID);
        cards.add(Burst.ID);
        cards.add(CorpseExplosion.ID);
        return cards;
    }
}
