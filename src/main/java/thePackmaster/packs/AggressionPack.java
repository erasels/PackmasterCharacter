package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.aggressionpack.*;

import java.util.ArrayList;

public class AggressionPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("AggressionPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public AggressionPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(5, 2, 2, 4, 3, "Strength", "Stances", "Attacks"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Vindicate.ID);
        cards.add(DarkLance.ID);
        cards.add(FuriousAssault.ID);
        cards.add(Slam.ID);
        cards.add(Decapitate.ID);
        cards.add(TitansStrength.ID);
        cards.add(Epiphany.ID);
        cards.add(InnerFury.ID);
        cards.add(LunaticRage.ID);
        cards.add(Animosity.ID);
        return cards;
    }
}
