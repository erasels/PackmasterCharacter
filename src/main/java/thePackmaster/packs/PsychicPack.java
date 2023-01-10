package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.psychicpack.*;

import java.util.ArrayList;

public class PsychicPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PsychicPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public PsychicPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        /*cards.add(TheFool.ID);
        cards.add(Justice.ID);
        cards.add(TheHermit.ID);
        cards.add(TheHangedMan.ID);
        cards.add(TheTower.ID);
        cards.add(Death.ID);
        cards.add(Temperance.ID);
        cards.add(TheHierophant.ID);
        cards.add(TheSun.ID);
        cards.add(TheChariot.ID);*/
        return cards;
    }
}
