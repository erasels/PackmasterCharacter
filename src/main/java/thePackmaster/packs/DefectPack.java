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
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BallLightning.ID);
        cards.add(Coolheaded.ID);
        cards.add(Hologram.ID);
        cards.add(Defragment.ID);
        cards.add(Sunder.ID);
        cards.add(Chaos.ID);
        cards.add(Fusion.ID);
        cards.add(Skim.ID);
        cards.add(Seek.ID);
        cards.add(AllForOne.ID);
        return cards;
    }
}
