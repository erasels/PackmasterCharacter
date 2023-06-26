package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.evenoddpack.*;

import java.util.ArrayList;

public class EvenOddPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("EvenOddPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String GRAY = "[#808080]";
    
    public EvenOddPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 4, 4, 3));
    }
    
    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(SwordAndBoard.ID);
        cards.add(QuickReflex.ID);
        cards.add(PrimeDirective.ID);
        cards.add(WitzBolt.ID);
        cards.add(ElementOfSurprise.ID);
        cards.add(GammaWard.ID);
        cards.add(WindUpBomb.ID);
        cards.add(LeftHook.ID);
        cards.add(Battlecry.ID);
        cards.add(Flourish.ID);
        return cards;
    }
}