package thePackmaster.patches.colorlesspack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import thePackmaster.cardmodifiers.colorlesspack.IsGhostModifier;
import thePackmaster.vfx.colorlesspack.PurpleNonmovingBlur;

public class GhostPatches {
    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class MakeStatEquivalentCopy {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(GameActionManager __instance) {
            AbstractCard c = __instance.cardQueue.get(0).card;
            if (c != null) {
                if (CardModifierManager.hasModifier(c, IsGhostModifier.ID)) {
                    if (__instance.cardQueue.get(0).monster == null) {
                        __instance.cardQueue.get(0).randomTarget = true;
                    }
                    AbstractDungeon.player.hand.removeCard(c);
                    IsGhostModifier mod = (IsGhostModifier) CardModifierManager.getModifiers(c, IsGhostModifier.ID).get(0);
                    AbstractDungeon.player.cardInUse = mod.ghost;
                    __instance.cardQueue.get(0).card = mod.ghost;
                    mod.ghost.current_x = c.current_x;
                    mod.ghost.current_y = c.current_y;
                    CardCrawlGame.sound.play("ATTACK_WHIFF_2");
                    for (int itr = 0; itr < 90; itr++) {
                        AbstractDungeon.effectsQueue.add(new PurpleNonmovingBlur(mod.ghost.current_x, mod.ghost.current_y));
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(GameActionManager.class, "usingCard");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }


    @SpirePatch(clz = CardModifierManager.class, method = "addModifier")
    public static class CopyModifiers {
        public static void Prefix(AbstractCard card, AbstractCardModifier mod) {
            if (mod.shouldApply(card) && !(mod instanceof IsGhostModifier) && !mod.isInherent(card)) {
                if (CardModifierManager.hasModifier(card, IsGhostModifier.ID)) {
                    IsGhostModifier ghostMod = (IsGhostModifier) CardModifierManager.getModifiers(card, IsGhostModifier.ID).get(0);
                    CardModifierManager.addModifier(ghostMod.ghost, mod.makeCopy());
                }
            }
        }
    }
}