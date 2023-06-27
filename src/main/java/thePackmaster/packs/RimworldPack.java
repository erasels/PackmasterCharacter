package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.rimworldpack.*;

import java.util.ArrayList;

public class RimworldPack  extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(RimworldPack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public RimworldPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(3, 3, 4, 2, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(ChainShotgun.ID);
        cards.add(EatWithoutTable.ID);
        cards.add(TaintedWeapon.ID);
        cards.add(TradeBeacon.ID);
        cards.add(KillThirst.ID);
        cards.add(Luciferium.ID);
        cards.add(SpikeTrap.ID);
        cards.add(Pyromaniac.ID);
        cards.add(BurningPassion.ID);
        cards.add(Catharsis.ID);
        return cards;
    }
}