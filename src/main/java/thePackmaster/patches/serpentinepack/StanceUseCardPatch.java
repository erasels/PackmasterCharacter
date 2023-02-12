package thePackmaster.patches.serpentinepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.patches.hermitpack.EnumPatch;
import thePackmaster.util.AbstractUseCardStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StanceUseCardPatch {

    @SpirePatch(
            clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCard.class,
                    AbstractCreature.class
            }
    )
    public static class sfx
    {
        @SpirePostfixPatch
        public static void Postfix(UseCardAction __instance, AbstractCard __card, AbstractCreature __target)
        {
            if (AbstractDungeon.player.stance instanceof AbstractUseCardStance){
                AbstractUseCardStance tmp = (AbstractUseCardStance) AbstractDungeon.player.stance;
                tmp.onAfterUseCard(__instance, __target);
            }
        }

    }


}