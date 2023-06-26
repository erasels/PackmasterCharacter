package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.downfallpack.*;

import java.util.ArrayList;

public class DownfallPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DownfallPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DownfallPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 3, 3, 3, 3, "Stances", "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AwakenDeath.ID);
        cards.add(Chronoboost.ID);
        cards.add(DefensiveMode.ID);
        cards.add(Execute.ID);
        cards.add(GhostflameStrike.ID);
        cards.add(HyperBeam.ID);
        cards.add(InvincibleStrength.ID);
        cards.add(PrepareCrush.ID);
        cards.add(ShapersBlessing.ID);
        cards.add(YouAreMine.ID);
        cards.add(SlimeCrush.ID);
        return cards;
    }
}
