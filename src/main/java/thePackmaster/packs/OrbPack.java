package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Consume;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.arcanapack.*;
import thePackmaster.cards.orbpack.*;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import java.util.ArrayList;

public class OrbPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("OrbPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public OrbPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Allocate.ID);
        cards.add(Augment.ID);
        cards.add(Charge.ID);
        cards.add(Emptiness.ID);
        cards.add(Fear.ID);
        cards.add(IcicleCrash.ID);
        cards.add(MaterialBoost.ID);
        cards.add(Stockpile.ID);
        cards.add(Thunderstruck.ID);
        cards.add(Consume.ID);
        return cards;
    }

    public void initializePack() {
        for (String s : getCards()) {
            AbstractCard c = CardLibrary.getCard(s);
            if (c == null) {
                System.out.println("CARD FOR PACK NOT FOUND: " + s);
                //And then it'll crash.
            }
            SpireAnniversary5Mod.cardParentMap.put(c.cardID, packID);
            SpireAnniversary5Mod.cardClassParentMap.put(c.getClass(), packID);
            cards.add(c.makeStatEquivalentCopy());
        }

        SpireAnniversary5Mod.cardParentMap.put(packID, packID);
        previewPackCard = new CardPackPreview(packID, "blue/skill/chaos", this);
    }
}