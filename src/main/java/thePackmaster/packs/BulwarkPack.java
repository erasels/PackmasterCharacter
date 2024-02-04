package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.ConserveBattery;
import com.megacrit.cardcrawl.cards.blue.Equilibrium;
import com.megacrit.cardcrawl.cards.blue.SteamBarrier;
import com.megacrit.cardcrawl.cards.green.AfterImage;
import com.megacrit.cardcrawl.cards.green.Backflip;
import com.megacrit.cardcrawl.cards.purple.Sanctity;
import com.megacrit.cardcrawl.cards.purple.SpiritShield;
import com.megacrit.cardcrawl.cards.purple.Swivel;
import com.megacrit.cardcrawl.cards.red.Juggernaut;
import com.megacrit.cardcrawl.cards.red.SecondWind;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class BulwarkPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BulwarkPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BulwarkPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(1, 5, 2, 3, 4, PackSummary.Tags.Exhaust));
        this.hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Backflip.ID);
        cards.add(ConserveBattery.ID);
        cards.add(SteamBarrier.ID);
        cards.add(Swivel.ID);
        cards.add(Equilibrium.ID);
        cards.add(Sanctity.ID);
        cards.add(SecondWind.ID);
        cards.add(Juggernaut.ID);
        cards.add(AfterImage.ID);
        cards.add(SpiritShield.ID);
        return cards;
    }
}

