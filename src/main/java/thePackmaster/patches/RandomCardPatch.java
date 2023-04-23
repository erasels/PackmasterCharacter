package thePackmaster.patches;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.EverythingFix;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.ThePackmaster;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "returnRandomCard"
)
public class RandomCardPatch {
    @SpirePrefixPatch
    public static SpireReturn<AbstractCard> IfThePoolAintReadyYet() {
        if (AbstractDungeon.player instanceof ThePackmaster &&
            AbstractDungeon.srcCommonCardPool.isEmpty()) { //If one is empty assume all 3 are empty

            CardGroup packmasterCards = EverythingFix.Fields.cardGroupMap.get(ThePackmaster.Enums.PACKMASTER_RAINBOW);
            AbstractCard.CardRarity rarity = AbstractDungeon.rollRarity();
            ArrayList<AbstractCard> list = new ArrayList<>();
            for (AbstractCard c : packmasterCards.group)
                if (c.rarity == rarity)
                    list.add(c);

            return SpireReturn.Return(list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1)));
        }
        return SpireReturn.Continue();
    }
}
