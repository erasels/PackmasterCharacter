package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.psychicpack.*;
import thePackmaster.cards.psychicpack.OneIsEnough;

import java.util.ArrayList;

public class PsychicPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PsychicPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public PsychicPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 2, 3, 4, 4, "Exhaust"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(RuleCancel.ID);
        cards.add(Brainwave.ID);
        cards.add(TelephonePole.ID);
        cards.add(Gravity.ID);
        cards.add(Repel.ID);
        cards.add(OneIsEnough.ID);
        cards.add(PhantomStep.ID);
        cards.add(Curse.ID);
        cards.add(MalleableFire.ID);
        cards.add(DeepDream.ID);
        cards.add(MoldedFire.ID);
        return cards;
    }
}
