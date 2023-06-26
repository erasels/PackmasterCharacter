package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.womaninbluepack.*;

import java.util.ArrayList;

public class WomanInBluePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WomanInBluePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public WomanInBluePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 2, 4, 4, 2, "Exhaust"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BlockVial.ID);
        cards.add(ChaosVial.ID);
        cards.add(DynamicVial.ID);
        cards.add(ExplosiveVial.ID);
        cards.add(FireVial.ID);
        cards.add(MetallicVial.ID);
        cards.add(QuickBrew.ID);
        cards.add(ToxicVial.ID);
        cards.add(AttackVial.ID);
        cards.add(VialOfMemory.ID);
        return cards;
    }
}

