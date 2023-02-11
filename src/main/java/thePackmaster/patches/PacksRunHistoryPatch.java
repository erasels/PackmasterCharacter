package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.runHistory.RunHistoryScreen;
import com.megacrit.cardcrawl.screens.stats.RunData;
import javassist.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class PacksRunHistoryPatch {
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("RunHistory")).TEXT;

    @SpirePatch(clz = CardCrawlGame.class, method = SpirePatch.CONSTRUCTOR)
    public static class PacksField {
        @SpireRawPatch
        public static void addPacks(CtBehavior ctBehavior) throws NotFoundException, CannotCompileException {
            CtClass runData = ctBehavior.getDeclaringClass().getClassPool().get(RunData.class.getName());

            String fieldSource = "public java.util.List packmaster_packs;";

            CtField field = CtField.make(fieldSource, runData);

            runData.addField(field);
        }
    }

    @SpirePatch(clz = Metrics.class, method = "gatherAllData")
    public static class GatherAllDataPatch {
        @SpirePostfixPatch
        public static void gatherAllDataPatch(Metrics __instance, boolean death, boolean trueVictor, MonsterGroup monsters) {
            if (AbstractDungeon.player.chosenClass.equals(ThePackmaster.Enums.THE_PACKMASTER)) {
                ReflectionHacks.privateMethod(Metrics.class, "addData", Object.class, Object.class)
                        .invoke(__instance, "packmaster_packs", SpireAnniversary5Mod.currentPoolPacks.stream().map(p -> p.packID).collect(Collectors.toList()));
            }
        }
    }

    @SpirePatch(clz = RunHistoryScreen.class, method = "renderRunHistoryScreen")
    public static class DisplayPacks {
        @SuppressWarnings({"unchecked"})
        @SpireInsertPatch(locator = Locator.class, localvars = { "header2x", "yOffset"})
        public static void displayPacks(RunHistoryScreen __instance, SpriteBatch sb, float header2x, @ByRef float[] yOffset) throws NoSuchFieldException, IllegalAccessException {
            RunData runData = ReflectionHacks.getPrivate(__instance, RunHistoryScreen.class, "viewedRun");
            if (!runData.character_chosen.equals(ThePackmaster.Enums.THE_PACKMASTER.name())) {
                return;
            }

            Field field = runData.getClass().getField("packmaster_packs");
            List<String> packmaster_packs = (List<String>)field.get(runData);
            if (packmaster_packs == null || packmaster_packs.isEmpty()) {
                return;
            }

            String headerText = TEXT[0];
            List<String> packNames = packmaster_packs.stream().map(packID -> {
                AbstractCardPack pack = SpireAnniversary5Mod.packsByID.getOrDefault(packID, null);
                return pack != null ? pack.name : packID;
            }).collect(Collectors.toList());
            String packsText = String.join(", ", packNames);

            ReflectionHacks.RMethod renderSubHeadingWithMessageMethod = ReflectionHacks.privateMethod(RunHistoryScreen.class, "renderSubHeadingWithMessage", SpriteBatch.class, String.class, String.class, float.class, float.class);
            renderSubHeadingWithMessageMethod.invoke(__instance, sb, headerText, packsText, header2x, yOffset[0]);

            ReflectionHacks.RMethod screenPosY = ReflectionHacks.privateMethod(RunHistoryScreen.class, "screenPosY", float.class);
            yOffset[0] = yOffset[0] - (float)screenPosY.invoke(__instance, 40.0F);
        }

        public static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher matcher = new Matcher.MethodCallMatcher(RunHistoryScreen.class, "renderRelics");
                return LineFinder.findInOrder(ctMethodToPatch, matcher);
            }
        }
    }
}
