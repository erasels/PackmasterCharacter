package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class DefectPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DefectPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DefectPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 4, 3, 4, 3, "Orbs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BallLightning.ID);
        cards.add(Coolheaded.ID);
        cards.add(Hologram.ID);
        cards.add(Defragment.ID);
        cards.add(Capacitor.ID);
        cards.add(Recursion.ID);
        cards.add(DoomAndGloom.ID);
        cards.add(Glacier.ID);
        cards.add(MultiCast.ID);
        cards.add(BiasedCognition.ID);
        return cards;
    }
}
