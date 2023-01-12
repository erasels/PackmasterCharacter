package thePackmaster.packs;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.pixiepack.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PixiePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PixiePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    private static ArrayList<AbstractCard> possibleToGenerate;

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

    public static void fillGenerateList()
    {
        possibleToGenerate = new ArrayList<>();
        for (AbstractCard C : CardLibrary.getAllCards())
        {
            if (isForeign(C)
                    && (C.rarity == AbstractCard.CardRarity.COMMON || C.rarity == AbstractCard.CardRarity.UNCOMMON || C.rarity == AbstractCard.CardRarity.RARE)
                    && (!C.hasTag(AbstractCard.CardTags.HEALING))
                    && (C.type != AbstractCard.CardType.STATUS && C.type != AbstractCard.CardType.CURSE)){
                int amt = 4;
                if (C.rarity== AbstractCard.CardRarity.UNCOMMON) amt = 2 ;
                if (C.rarity== AbstractCard.CardRarity.RARE) amt = 1 ;
                for(int i = 0; i < amt; i++) {
                    possibleToGenerate.add(C);
                }
            }
        }
    }

    public static AbstractCard pixieGenerate(Integer cost, Enum color, AbstractCard.CardType type) {
        if (possibleToGenerate == null) fillGenerateList();
        ArrayList<AbstractCard> AllCards = new ArrayList<>();
        AllCards.addAll(possibleToGenerate);
        AllCards.removeIf(C -> (cost != null && C.cost != cost) || (type != null && C.type != type) || (type != null && C.color != color));
        AbstractCard output = null;
        output = AllCards.get(AbstractDungeon.cardRandomRng.random(0, AllCards.size() - 1));
        return output.makeStatEquivalentCopy();
    }
}
