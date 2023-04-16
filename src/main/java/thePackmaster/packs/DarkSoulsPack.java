package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.darksoulspack.*;

import java.util.ArrayList;

public class DarkSoulsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DarkSoulsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DarkSoulsPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(MorionBlade.ID);
        cards.add(Bloodlust.ID);
        cards.add(IronFlesh.ID);
        cards.add(PrisonersChain.ID);
        cards.add(LifehuntScythe.ID);
        cards.add(Remedy.ID);
        cards.add(BlueTearstone.ID);
        cards.add(EmbraceHollowing.ID);
        cards.add(SmoughsHammer.ID);
        cards.add(CloranthyRing.ID);
        return cards;
    }
}