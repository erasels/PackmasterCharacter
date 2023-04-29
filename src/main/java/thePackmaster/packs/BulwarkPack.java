package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.GeneticAlgorithm;
import com.megacrit.cardcrawl.cards.blue.SteamBarrier;
import com.megacrit.cardcrawl.cards.green.AfterImage;
import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.cards.purple.SpiritShield;
import com.megacrit.cardcrawl.cards.purple.WaveOfTheHand;
import com.megacrit.cardcrawl.cards.red.BodySlam;
import com.megacrit.cardcrawl.cards.red.FeelNoPain;
import com.megacrit.cardcrawl.cards.red.Juggernaut;
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
        super(ID, NAME, DESC, AUTHOR);
        this.hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BodySlam.ID);
        cards.add(PiercingWail.ID);
        cards.add(SteamBarrier.ID);
        cards.add(FeelNoPain.ID);
        cards.add(Blur.ID);
        cards.add(GeneticAlgorithm.ID);
        cards.add(WaveOfTheHand.ID);
        cards.add(Juggernaut.ID);
        cards.add(AfterImage.ID);
        cards.add(SpiritShield.ID);
        return cards;
    }
}

