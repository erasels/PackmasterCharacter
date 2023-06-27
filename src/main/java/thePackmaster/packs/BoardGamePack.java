package thePackmaster.packs;

import basemod.helpers.CardTags;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.boardgamepack.*;

import java.util.ArrayList;

public class BoardGamePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(BoardGamePack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BoardGamePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 3, 2, 4, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(AllIn.ID);
        cards.add(D20.ID);
        cards.add(FlickDice.ID);
        cards.add(GoToJail.ID);
        cards.add(LevelUp.ID);
        cards.add(PerceptionCheck.ID);
        cards.add(PocketAce.ID);
        cards.add(Reroller.ID);
        cards.add(RollToMove.ID);
        cards.add(ThrowChips.ID);
        return cards;
    }
}
