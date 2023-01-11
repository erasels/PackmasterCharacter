package thePackmaster.patches.warlockpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.warlockpack.Imp;

@SpirePatch(clz = CardGroup.class, method = "addToHand")
@SpirePatch(clz = CardGroup.class, method = "addToTop")
@SpirePatch(clz = CardGroup.class, method = "addToBottom")
@SpirePatch(clz = CardGroup.class, method = "addToRandomSpot")
public class TriggerImpOnAddToHandPatch {
    @SpirePostfixPatch
    public static void trigger(CardGroup __instance, AbstractCard c) {
        if (AbstractDungeon.player != null && __instance == AbstractDungeon.player.hand && c instanceof Imp) {
            ((Imp)c).triggerWhenAddedToHand();
        }
    }
}
