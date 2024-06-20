package thePackmaster.patches.creativitypack;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.util.creativitypack.JediUtil;

@SpirePatch(clz = StSLib.class, method = "onCreateCard", paramtypez = {AbstractCard.class})
public class AccumulativeStrikePatch {
    @SpirePostfixPatch
    public static void onCreateCard(AbstractCard c) {
        JediUtil.onGenerateCardMidcombat(c);
    }
}
