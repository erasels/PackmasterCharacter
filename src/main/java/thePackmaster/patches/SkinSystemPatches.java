package thePackmaster.patches;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;
import thePackmaster.ThePackmaster;

import java.util.HashMap;
import java.util.Map;

public class SkinSystemPatches {
    //To prevent memory leaks caused by constantly instantiating an atlas with loadAnimation, we cache
    private static Map<String, TextureAtlas> atlasCache = new HashMap<>();

    @SpirePatch2(clz = AbstractCreature.class, method = "loadAnimation")
    public static class PopulateCache {
        @SpirePostfixPatch
        public static void catchAtlas(AbstractCreature __instance, String atlasUrl) {
            if (__instance instanceof ThePackmaster) {
                atlasCache.putIfAbsent(atlasUrl, ((ThePackmaster) __instance).getAtlas());
            }
        }

        @SpireInstrumentPatch
        public static ExprEditor skipAtlasLoadingIfExists() {
            return new AtlasNewExprEditor();
        }

        // surround the new atlas init with an if-else to check if it is cached already
        public static class AtlasNewExprEditor extends ExprEditor {
            @Override
            public void edit(NewExpr newExpr) throws CannotCompileException {
                if (newExpr.getClassName().equals(TextureAtlas.class.getName())) {
                    newExpr.replace(
                            String.format(
                                    "if(%s.hasAtlas(atlasUrl, this)) {" +
                                            "$_ = %s.getAtlas(atlasUrl);" +
                                            "} else {" +
                                            "$_ = $proceed($$);" +
                                            "}",
                                    AtlasNewExprEditor.class.getName(),
                                    AtlasNewExprEditor.class.getName()
                            )
                    );
                }
            }

            public static boolean hasAtlas(String atlasUrl, AbstractCreature instance) {
                return instance instanceof ThePackmaster && atlasCache.containsKey(atlasUrl);
            }

            public static TextureAtlas getAtlas(String atlasUrl) {
                return atlasCache.get(atlasUrl);
            }
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "initializeClass")
    public static class FixNullUnsafeCall {
        @SpireInstrumentPatch
        public static ExprEditor skipAtlasLoadingIfExists() {
            return new SetImageEditor();
        }

        // Don't call loadImage if null was passed
        public static class SetImageEditor extends ExprEditor {
            @Override
            public void edit(MethodCall match) throws CannotCompileException {
                if (match.getClassName().equals(ImageMaster.class.getName()) && match.getMethodName().equals("loadImage")) {
                    match.replace(
                            String.format(
                                    "if(%s.isPM(this) && $1 == null) {" +
                                            "$_ = null;" +
                                    "} else {" +
                                            "$_ = $proceed($$);" +
                                    "}",
                                    SetImageEditor.class.getName()
                            )
                    );
                }
            }

            public static boolean isPM(AbstractPlayer instance) {
                return instance instanceof ThePackmaster;
            }
        }
    }
}
