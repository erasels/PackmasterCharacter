package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.goddessofexplosionspack.*;

import java.util.ArrayList;

public class GoddessOfExplosionsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("GoddessOfExplosionsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public GoddessOfExplosionsPack() {
        super(ID, NAME, DESC, AUTHOR,CREDITS, new AbstractCardPack.PackSummary(4, 2, 3, 1, 5, "Powers"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(ChainReaction.ID);
        cards.add(WaveMotionCannon.ID);
        cards.add(Redshift.ID);
        cards.add(Countdown.ID);
        cards.add(AtomicPiledriver.ID);
        cards.add(NuclearFusion.ID);
        cards.add(MoreExplosions.ID);
        cards.add(Meltdown.ID);
        cards.add(EveOfDestruction.ID);
        cards.add(KillerQueen.ID);
        cards.add(ExplosiveForm.ID);
        return cards;
    }
}

