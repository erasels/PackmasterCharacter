package thePackmaster.patches.psychicpack.occult;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "useCard"
)
public class UseOccultCardCost {
    private static boolean reset = false;
    private static int temp = 0;

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void h(AbstractPlayer __instance, AbstractCard c, AbstractMonster m, int energyOnUse)
    {
        if (OccultFields.isOccult.get(c) && c.costForTurn > EnergyPanel.totalCount)
        {
            //occult card played with more cost than current energy
            temp = c.costForTurn;
            c.costForTurn = EnergyPanel.totalCount;
            reset = true;
        }
    }

    @SpireInsertPatch(
            locator = SecondLocator.class
    )
    public static void hh(AbstractPlayer __instance, AbstractCard c, AbstractMonster m, int energyOnUse)
    {
        if (reset)
        {
            c.costForTurn = temp;
            reset = false;
        }
    }

    private static class Locator extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "costForTurn");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }

    private static class SecondLocator extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "canUseAnyCard");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
