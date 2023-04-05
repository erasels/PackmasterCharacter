package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import sun.misc.Perf;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.brickpack.*;
import thePackmaster.cards.fueledpack.*;

import java.util.ArrayList;

public class BrickPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(BrickPack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BrickPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(HeavyBrick.ID);
        cards.add(LooseBrick.ID);
        cards.add(Demolish.ID);
        cards.add(HastyConstruction.ID);
        cards.add(Blueprints.ID);
        cards.add(Crenellations.ID);
        cards.add(LeaningTower.ID);
        cards.add(PerfectedBrick.ID);
        cards.add(ThrowDebris.ID);
        cards.add(Jenga.ID);
        cards.add(BrickGolem.ID);
        cards.add(Salvage.ID);
        return cards;
    }
}
