package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.clawpack.*;
import thePackmaster.cards.downfallpack.*;

import java.util.ArrayList;

public class ClawPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("ClawPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public ClawPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(5, 2, 1, 2, 5, "Tokens"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(PMClaw.ID);
        cards.add(Alclawmize.ID);
        cards.add(ClawForOne.ID);
        cards.add(CloakAndClaw.ID);
        cards.add(Conclawd.ID);
        cards.add(InfiniteClaws.ID);
        cards.add(MutateClaw.ID);
        cards.add(SearingClaw.ID);
        cards.add(TalkToTheClaw.ID);
        cards.add(TwinClaw.ID);
        cards.add(GhostClaw.ID);
        return cards;
    }

}
