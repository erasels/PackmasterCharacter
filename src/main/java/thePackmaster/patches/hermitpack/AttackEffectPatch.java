package thePackmaster.patches.hermitpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.SpireAnniversary5Mod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AttackEffectPatch {
    @SpirePatch(clz = FlashAtkImgEffect.class, method = "loadImage")
    public static class vfx
    {
        @SpirePrefixPatch
        public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect ___effect)
        {
            if (___effect == EnumPatch.HERMIT_GUN || ___effect == EnumPatch.HERMIT_GUN2 || ___effect == EnumPatch.HERMIT_GUN3) {
                return SpireReturn.Return(ImageMaster.ATK_BLUNT_LIGHT);
            }

            return SpireReturn.Continue();
        }

    }

    @SpirePatch(clz = FlashAtkImgEffect.class, method = "playSound")
    public static class sfx
    {
        @SpirePrefixPatch
        public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect effect)
        {
            if (effect == EnumPatch.HERMIT_GUN) {
                CardCrawlGame.sound.playV(SpireAnniversary5Mod.GUN1_KEY, 1.25f); // Sound Effect
            }
            else if (effect == EnumPatch.HERMIT_GUN2) {
                CardCrawlGame.sound.playV(makeID(SpireAnniversary5Mod.GUN2_KEY), 1.25f); // Sound Effect
            }
            else if (effect == EnumPatch.HERMIT_GUN3) {
                CardCrawlGame.sound.playV(makeID(SpireAnniversary5Mod.GUN3_KEY), 1.25f); // Sound Effect
            }
            else {
                return SpireReturn.Continue();
            }

            return SpireReturn.Return(null);
        }

    }
}
