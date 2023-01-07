package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.summonspack.*;

import java.util.ArrayList;

public class SummonsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(SummonsPack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public SummonsPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(SummonBulldog.ID);
        cards.add(SummonVegetables.ID);
        cards.add(SummonTrees.ID);
        cards.add(SummonElephant.ID);
        cards.add(SummonBrambles.ID);
        cards.add(SummonPython.ID);
        cards.add(SummonBees.ID);
        cards.add(DemonicRitual.ID);
        cards.add(SummonPandas.ID);
        cards.add(Control.ID);
        return cards;
    }
}
