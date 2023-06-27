package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.secretlevelpack.*;

import java.util.ArrayList;

public class SecretLevelPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("SecretLevelPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public SecretLevelPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 3, 3, 2, 4, "Debuffs"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(MysteryBox.ID);
        cards.add(SurpriseAttack.ID);
        cards.add(EscapeClause.ID);
        cards.add(Banana.ID);
        cards.add(Joust.ID);
        cards.add(Conglaturation.ID);
        cards.add(EnoughTalk.ID);
        cards.add(DanseMacabre.ID);
        cards.add(TheDonut.ID);
        cards.add(AchievementHunter.ID);
        return cards;
    }
}
