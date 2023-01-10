package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.metapack.*;
import thePackmaster.cards.monsterhunterpack.*;

import java.awt.*;
import java.util.ArrayList;

public class MonsterHunterPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("MonsterHunterPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public MonsterHunterPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(CarvingKnife.ID);
        cards.add(HuntingStrike.ID);
        cards.add(Greatsword.ID);
        cards.add(HuntingContract.ID);
        cards.add(HuntingInstincts.ID);
        cards.add(Focus.ID);
        cards.add(TranqBomb.ID);
        cards.add(Paintball.ID);
        cards.add(PerfectDodge.ID);
        cards.add(HunterRank.ID);
        return cards;
    }
}

