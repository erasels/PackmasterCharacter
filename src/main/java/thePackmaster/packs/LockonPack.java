package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.lockonpack.*;
import thePackmaster.cards.lockonpack.special.DC;

import java.util.ArrayList;

public class LockonPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("LockonPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public LockonPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 2, 3, 2, 4, "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AC.ID);
        cards.add(DC.ID);
        cards.add(BlockOn.ID);
        cards.add(Bruteforce.ID);
        cards.add(LightsOut.ID);
        cards.add(GlockOn.ID);
        cards.add(LocknLoad.ID);
        cards.add(Reiji.ID);
        cards.add(LightningRod.ID);
        cards.add(TunnelVision.ID);

        cards.add(StaticDischarge.ID);

        return cards;
    }
}
