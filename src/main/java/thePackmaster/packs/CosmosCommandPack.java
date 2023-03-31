package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.cosmoscommandpack.*;

import java.util.ArrayList;

public class CosmosCommandPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("CosmosCommandPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public CosmosCommandPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(ArcingEnergy.ID);
        cards.add(AstralFracture.ID);
        cards.add(Cognition.ID);
        cards.add(EternalStrike.ID);
        cards.add(FalseGrit.ID);
        cards.add(HeatEngine.ID);
        cards.add(RefractEnergy.ID);
        cards.add(RopeTrick.ID);
        cards.add(Subspace.ID);
        cards.add(SubtleKnife.ID);
        return cards;
    }
}