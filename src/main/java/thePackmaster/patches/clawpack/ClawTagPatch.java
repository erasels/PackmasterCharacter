package thePackmaster.patches.clawpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import thePackmaster.SpireAnniversary5Mod;

@SpirePatch(
        clz = Claw.class,
        method = SpirePatch.CONSTRUCTOR
)
public class ClawTagPatch {
        public static void Postfix(AbstractCard __instance) {
            __instance.tags.add(SpireAnniversary5Mod.CLAW);
        }
    }
