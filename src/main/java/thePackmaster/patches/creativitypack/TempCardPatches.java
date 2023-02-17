package thePackmaster.patches.creativitypack;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import thePackmaster.util.JediUtil;

@SpirePatch(clz = ShowCardAndAddToDiscardEffect.class, method = "update")
@SpirePatch(clz = ShowCardAndAddToHandEffect.class, method = "update")
@SpirePatch(clz = ShowCardAndAddToDrawPileEffect.class, method = "update")
public class TempCardPatches {
    @SpirePrefixPatch
    public static void Prefix(AbstractGameEffect __instance, float ___EFFECT_DUR, AbstractCard ___card) {
        if (__instance.duration == ___EFFECT_DUR) {
            JediUtil.onGenerateCardMidcombat(___card);
        }
    }
}
