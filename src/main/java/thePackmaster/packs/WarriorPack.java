package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.warriorpack.*;

import java.util.ArrayList;

public class WarriorPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WarriorPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public WarriorPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 1, 3, 2, "Debuffs", "Attacks"));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Bolas.ID);
        cards.add(Chained.ID);
        cards.add(Fall.ID);
        cards.add(FFBlade.ID);
        cards.add(Polishing.ID);
        cards.add(Rapier.ID);
        cards.add(StormStrike.ID);
        cards.add(Turtle.ID);
        cards.add(WarSong.ID);
        cards.add(WorkHammer.ID);
        return cards;
    }


}
