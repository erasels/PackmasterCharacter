package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Net;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.SpireAnniversary5Mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static thePackmaster.ThePackmaster.Enums.THE_PACKMASTER;

public class MetricsPatches {
    private static final String PMMetricsURL = "http://metrics.twentyfourfuckingletters.com/";
    private static final Logger logger = LogManager.getLogger(MetricsPatches.class);

    public static ArrayList<HashMap> packChoices = new ArrayList<>();

    @SpirePatch(clz = Metrics.class, method = "sendPost", paramtypez = {String.class, String.class})
    public static class SendPutInsteadOfPostPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = "httpRequest")
        public static void Insert(Metrics metrics, String url, String fileName, Net.HttpRequest httpRequest) {
            if (AbstractDungeon.player.chosenClass == THE_PACKMASTER) {
                httpRequest.setMethod("PUT");
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior method) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(Net.HttpRequest.class, "setContent");
                return LineFinder.findInOrder(method, matcher);
            }
        }
    }


    @SpirePatch(clz = GameOverScreen.class, method = "shouldUploadMetricData")
    public static class ShouldUploadMetricData {
        @SpirePostfixPatch
        public static boolean patch(boolean returnValue) {
            if (AbstractDungeon.player.chosenClass == THE_PACKMASTER && AbstractDungeon.floorNum > 5) {
                returnValue = Settings.UPLOAD_DATA;
            }
            return returnValue;
        }

    }

    @SpirePatch(clz = Metrics.class, method = "run")
    public static class RunPatch {
        @SpirePostfixPatch
        public static void patch(Metrics metrics) {
            if (metrics.type == Metrics.MetricRequestType.UPLOAD_METRICS && AbstractDungeon.player.chosenClass == THE_PACKMASTER) {
                try {
                    Method addDataMethod = Metrics.class.getDeclaredMethod("addData", Object.class, Object.class);
                    addDataMethod.setAccessible(true);
                    addDataMethod.invoke(metrics, "pmversion", findTheModVersion());
                    addDataMethod.invoke(metrics, "currentPacks", SpireAnniversary5Mod.currentPoolPacks.stream().map(p -> p.packID).collect(Collectors.joining(",")));

                    String filteredPacks = SpireAnniversary5Mod.unfilteredAllPacks.stream()
                            .filter(p -> !SpireAnniversary5Mod.allPacks.contains(p))
                            .map(p -> p.packID)
                            .collect(Collectors.joining(","));
                    addDataMethod.invoke(metrics, "filteredPacks", filteredPacks);

                    //Pack choices at the beginning formatted as Pack1|*Pack2|Pack3,Pack1... * signifying the selected pack
                    addDataMethod.invoke(metrics, "allPacksMode", SpireAnniversary5Mod.allPacksMode);
                    if(!SpireAnniversary5Mod.allPacksMode) {
                        addDataMethod.invoke(metrics, "packChoices", packChoices);
                    }

                    String otherModPackIDs = SpireAnniversary5Mod.unfilteredAllPacks.stream()
                            .filter(p -> !p.packID.startsWith(SpireAnniversary5Mod.modID))
                            .map(p -> p.packID)
                            .collect(Collectors.joining(","));
                    if(!otherModPackIDs.isEmpty())
                        addDataMethod.invoke(metrics, "otherModPacks", otherModPackIDs);

                    addDataMethod.invoke(metrics, "packSummariesEnabled", SpireAnniversary5Mod.showPackSummaries());

                    boolean draftEnabled = SpireAnniversary5Mod.getCustomDraftEnabled();
                    addDataMethod.invoke(metrics, "customDraftEnabled", draftEnabled);
                    if(draftEnabled)
                        addDataMethod.invoke(metrics, "customDraftConfiguration", SpireAnniversary5Mod.modConfig.getString("PackmasterCustomDraftSelection"));

                    addDataMethod.invoke(metrics, "pickedHat", SpireAnniversary5Mod.getLastPickedHatID());
                    addDataMethod.invoke(metrics, "enabledExpansionPacks", SpireAnniversary5Mod.isExpansionLoaded);


                    Method gatherAllDataMethod = Metrics.class.getDeclaredMethod("gatherAllData", boolean.class, boolean.class, MonsterGroup.class);
                    gatherAllDataMethod.setAccessible(true);
                    gatherAllDataMethod.invoke(metrics, metrics.death, metrics.trueVictory, metrics.monsters);

                    ReflectionHacks.RMethod sendPostMethod = ReflectionHacks.privateMethod(Metrics.class, "sendPost", String.class, String.class);
                    sendPostMethod.invoke(metrics, PMMetricsURL, null);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    logger.error("Exception while sending metrics", e);
                }
            }
        }

        private static String findTheModVersion() {
            return Arrays.stream(Loader.MODINFOS).filter(modInfo -> SpireAnniversary5Mod.modID.equals(modInfo.getIDName()))
                    .findFirst()
                    .map(modInfo -> modInfo.ModVersion.toString())
                    .orElse("Unknown");
        }

    }
}
