package thePackmaster.patches.evenoddpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ResetAttributesPatch {
    @SpirePatch2(clz = AbstractCard.class, method = "resetAttributes")
    public static class RemovePowerText {
        @SpirePrefixPatch
        public static void RemovePowerText(AbstractCard __instance) {
            __instance.initializeDescription();
        }
    }
}