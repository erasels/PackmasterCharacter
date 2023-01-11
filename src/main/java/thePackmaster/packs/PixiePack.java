package thePackmaster.packs;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.downfallpack.*;
import thePackmaster.cards.pixiepack.*;

import java.util.ArrayList;
import java.util.List;

public class PixiePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PixiePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public PixiePack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    public static class pixieTags
    {
        @SpireEnum public static AbstractCard.CardTags ENCHANTMENT;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BoosterPack.ID);
        cards.add(StardustShield.ID);
        cards.add(StarShower.ID);
        cards.add(PortalPunch.ID);
        cards.add(BackupRift.ID);
        cards.add(StarLane.ID);
        cards.add(NeutronStar.ID);
        cards.add(FastHands.ID);
        cards.add(DimensionBreach.ID);
        cards.add(Horizonbound.ID);
        return cards;
    }

    public static boolean isForeign(AbstractCard card)
    {
        return card.color != AbstractDungeon.player.getCardColor();
    }

    public static AbstractCard pixieGenerate(Integer cost, Enum color, AbstractCard.CardType type) {
        List<AbstractCard> AllCards = new ArrayList<>();
        for (AbstractCard C : CardLibrary.getAllCards())
        {
            if ((cost != null && C.cost != cost) || (color != null && C.color != color) || (type != null && C.type != type)) continue;
            if (isForeign(C))
            {
                int amt = 4;
                if (C.rarity== AbstractCard.CardRarity.UNCOMMON) amt = 2 ;
                if (C.rarity== AbstractCard.CardRarity.RARE) amt = 1 ;
                for(int i = 0; i < amt; i++) {
                    AllCards.add(C);
                }
            }
        }
        AbstractCard output = null;
        output = AllCards.get(AbstractDungeon.miscRng.random(0, AllCards.size() - 1));
        return output.makeStatEquivalentCopy();
    }
}
