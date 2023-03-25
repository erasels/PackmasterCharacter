package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.FocusPotion;
import com.megacrit.cardcrawl.potions.PotionOfCapacity;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.fueledpack.IridiumShield;
import thePackmaster.cards.fueledpack.RedHotSword;

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
        cards.add(RedHotSword.ID);
        cards.add(IridiumShield.ID);
        return cards;
    }

    @Override
    public ArrayList<String> getPackPotions() {
        ArrayList<String> list = new ArrayList<>();
        list.add(FocusPotion.POTION_ID);
        list.add(PotionOfCapacity.POTION_ID);
        return list;
    }
}
