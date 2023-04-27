package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.Expectation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.neow.NeowEvent;
import javassist.CtBehavior;
import javassist.expr.Expr;
import javassist.expr.FieldAccess;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.patches.compatibility.InfiniteSpirePatch;

@SpirePatch(
        clz = NeowEvent.class,
        method = "update"
)
@SpirePatch(
        cls = "downfall.events.HeartEvent",
        method = "update",
        optional = true
)
public class OpeningRunScreenPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void SetTheThing(AbstractEvent __instance) {
        if(!Settings.isEndless || AbstractDungeon.floorNum <= 1) {
            if (AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER) {
                if (SpireAnniversary5Mod.allPacksMode) {
                    SpireAnniversary5Mod.logger.info("Vex's All Packs Override Enabled. Skipping intro screen");
                    SpireAnniversary5Mod.currentPoolPacks.clear();
                    SpireAnniversary5Mod.currentPoolPacks.addAll(SpireAnniversary5Mod.allPacks);
                    CardCrawlGame.dungeon.initializeCardPools();
                    InfiniteSpirePatch.generateQuestsIfInfiniteSpireIsLoaded();
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
                    FieldAccess expr = (FieldAccess)toMatch;
                    return expr.getFieldName().equals("setPhaseToEvent");
                }
            };
            return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[1]};
        }
    }
}