package thePackmaster.patches.clawpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.defect.GashAction;
import thePackmaster.cards.clawpack.AbstractClawCard;

@SpirePatch(
        clz = GashAction.class,
        method = "update"
)
public class ClawUsePatch {

    @SpirePrefixPatch
    public static void Prefix(GashAction __instance) {
        AbstractClawCard.ClawUp(__instance.amount, true);

    }
}
