package thePackmaster.patches.rippack;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCardDescriptors;
import basemod.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup.RenderCardDescriptorsSCV;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CtBehavior;

import java.util.List;

public class TypeOverridePatch {
    @SpirePatch(clz= AbstractCard.class, method=SpirePatch.CLASS)
    private static class TypeOverrideField {
        public static SpireField<String> typeOverride = new SpireField<>(() -> null);
    }

    public static void setOverride(AbstractCard card, String type) {
        TypeOverrideField.typeOverride.set(card, type);
    }

    @SpirePatch(clz = RenderCardDescriptors.Text.class, method = "Insert")
    public static class OverrideTypeRenderPatch {

        @SpireInsertPatch(locator = Locator.class)
        public static void OverrideType(AbstractCard ___card, SpriteBatch sb, String[] text) {
            if (TypeOverridePatch.TypeOverrideField.typeOverride.get(___card) != null) {
                text[0] = TypeOverridePatch.TypeOverrideField.typeOverride.get(___card);
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(List.class, "add");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = RenderCardDescriptors.Frame.class, method = "Insert")
    public static class OverrideTypeSizePatch {

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"typeText", "descriptors"}
        )
        public static void OverrideType(AbstractCard ___card, SpriteBatch sb, float x, float y, float[] tOffset, float[] tWidth, @ByRef String[] typeText, @ByRef List<String>[] descriptors) {
            if (TypeOverridePatch.TypeOverrideField.typeOverride.get(___card) != null) {
                typeText[0] = TypeOverridePatch.TypeOverrideField.typeOverride.get(___card);
                GlyphLayout gl = new GlyphLayout();
                FontHelper.cardTypeFont.getData().setScale(1.0F);
                gl.setText(FontHelper.cardTypeFont, typeText[0]);
                tOffset[0] = (gl.width - 38.0F * Settings.scale) / 2.0F;
                tWidth[0] = (gl.width - 0.0F) / (32.0F * Settings.scale);
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(List.class, "add");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = RenderCardDescriptorsSCV.Frame.class, method = "Insert")
    public static class OverrideTypeSizePatchSCV {

        @SpireInsertPatch(locator = Locator.class, localvars = {"typeText", "descriptors"})
        public static void OverrideTypeSCV(SingleCardViewPopup __instance, SpriteBatch sb, AbstractCard ___card, float[] tOffset, float[] tWidth, @ByRef String[] typeText, @ByRef List<String>[] descriptors) {
            if (TypeOverridePatch.TypeOverrideField.typeOverride.get(___card) != null) {
                typeText[0] = TypeOverridePatch.TypeOverrideField.typeOverride.get(___card);
                GlyphLayout gl = new GlyphLayout();
                FontHelper.panelNameFont.getData().setScale(1.0F);
                gl.setText(FontHelper.panelNameFont, typeText[0]);
                tOffset[0] = (gl.width - 70.0F * Settings.scale) / 2.0F;
                tWidth[0] = (gl.width - 0.0F) / (62.0F * Settings.scale);
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(List.class, "add");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }
}
