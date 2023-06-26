package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class IroncladPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("IroncladPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public IroncladPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 3, 4, 2, "Strength"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Headbutt.ID);
        cards.add(ShrugItOff.ID);
        cards.add(ThunderClap.ID);
        cards.add(Whirlwind.ID);
        cards.add(BattleTrance.ID);
        cards.add(Rampage.ID);
        cards.add(Uppercut.ID);
        cards.add(Inflame.ID);
        cards.add(Barricade.ID);
        cards.add(DoubleTap.ID);
        return cards;
    }
}
