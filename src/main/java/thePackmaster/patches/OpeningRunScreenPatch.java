package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.Expectation;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.neow.NeowEvent;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.Expr;
import javassist.expr.FieldAccess;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;

import java.util.ArrayList;


public class OpeningRunScreenPatch {
    @SpirePatch(clz = NeowEvent.class, method = "update")
    @SpirePatch(cls = "downfall.events.HeartEvent", method = "update", optional = true)
    public static class NeowPatches {
        @SpireInsertPatch(locator = Locator.class)
        public static void SetTheThing(AbstractEvent __instance) {
            if (!Settings.isEndless || AbstractDungeon.floorNum <= 1) {
                if (AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER && SpireAnniversary5Mod.currentPoolPacks.isEmpty()) {
                    if (SpireAnniversary5Mod.allPacksMode) {
                        SpireAnniversary5Mod.logger.info("Vex's All Packs Override Enabled. Skipping intro screen");
                        SpireAnniversary5Mod.currentPoolPacks.clear();
                        SpireAnniversary5Mod.currentPoolPacks.addAll(SpireAnniversary5Mod.allPacks);
                        SpireAnniversary5Mod.initializeCardPools();
                    } else {
                        SpireAnniversary5Mod.logger.info("Packmaster is Ready To Do Thing");
                        SpireAnniversary5Mod.openedStarterScreen = false;
                        SpireAnniversary5Mod.doPackSetup = true;
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher(Expectation.FIELD_ACCESS) {
                    @Override
                    public boolean match(Expr toMatch) {
                        FieldAccess expr = (FieldAccess) toMatch;
                        return expr.getFieldName().equals("setPhaseToEvent");
                    }
                };
                return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[1]};
            }
        }
    }

    //Fixes the event not spawning with ActLikeIt's custom event on run start feature
    @SpirePatch2(cls="actlikeit.dungeons.CustomDungeon", method = SpirePatch.CONSTRUCTOR, paramtypes = {"actlikeit.dungeons.CustomDungeon", "com.megacrit.cardcrawl.characters.AbstractPlayer", "java.util.ArrayList"}, optional = true)
    public static class CustomNeowReplacementEventFix {
        @SpireInsertPatch(locator = Locator.class, localvars = {"ae"})
        public static void lookAtWhatIDoForYouDark(AbstractEvent ae) {
            NeowPatches.SetTheThing(ae);
        }

        public static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractEvent.class, "onEnterRoom");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }
}