package thePackmaster.patches.odditiespack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.random.Random;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PackmasterFoilPatches {

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class FoilField {
        public static SpireField<Boolean> foil = new SpireField<>(() -> false);
    }

    public static boolean isFoil(AbstractCard card) {
        return FoilField.foil.get(card);
    }

    public static void makeFoil(AbstractCard card) {
        FoilField.foil.set(card, true);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class FoilCopies {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance) {
            if (isFoil(__instance)) {
                makeFoil(__result);
            }

            return __result;
        }
    }


    // VISUAL STUFF

    public static final ShaderProgram ART_SHADER = new ShaderProgram(SpriteBatch.createDefaultShader().getVertexShaderSource(), Gdx.files.internal("anniv5Resources/shaders/odditiespack/foil_card_art.frag").readString(String.valueOf(StandardCharsets.UTF_8)));

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderPortrait"
    )
    public static class FoilSpecialArt {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static HashMap<String, Float> shiftAmts = new HashMap<>();

        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                oldShader = sb.getShader();
                sb.setShader(ART_SHADER);
                ART_SHADER.setUniformf("shift_amt", shiftAmts.computeIfAbsent(__instance.cardID, key -> {
                    Random rng = new Random((long) __instance.cardID.hashCode());
                    return 0.2F + rng.random(0F, 0.6F);
                }));
            }
        }

        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                sb.setShader(oldShader);
            }
        }
    }


//    @SpirePatch(
//            clz = AbstractCard.class,
//            method = "renderCardBg"
//    )
//    public static class FoilShiny {
//        private static ShaderProgram oldShader;
//        private static Color oldColor;
//
//        public static void Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
//            if (isFoil(__instance) && __instance.color != TheFishing.Enums.FISHING_COLOR && __instance.color != AbstractCard.CardColor.COLORLESS) {
//                oldShader = sb.getShader();
//                sb.setShader(shade);
//                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
//                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", hslcBackground);
//            }
//        }
//
//        public static void Postfix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
//            if (isFoil(__instance) && __instance.color != TheFishing.Enums.FISHING_COLOR && __instance.color != AbstractCard.CardColor.COLORLESS) {
//                sb.setShader(oldShader);
//                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", oldColor);
//            }
//        }
//    }

//    @SpirePatch(
//            clz = SingleCardViewPopup.class,
//            method = "renderCardBack"
//    )
//    public static class FoilShinySingleCardView {
//        private static ShaderProgram oldShader;
//        private static Color oldColor;
//
//        public static void Prefix(SingleCardViewPopup __instance, SpriteBatch sb) {
//            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
//            if (isFoil(card) && card.color != TheFishing.Enums.FISHING_COLOR && card.color != AbstractCard.CardColor.COLORLESS) {
//                oldShader = sb.getShader();
//                sb.setShader(shade);
//                oldColor = sb.getColor();
//                sb.setColor(hslcBackground);
//            }
//        }
//
//        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
//            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
//            if (isFoil(card) && card.color != TheFishing.Enums.FISHING_COLOR && card.color != AbstractCard.CardColor.COLORLESS) {
//                sb.setShader(oldShader);
//                sb.setColor(oldColor);
//            }
//        }
//    }
//
//    @SpirePatch(
//            clz = SingleCardViewPopup.class,
//            method = "renderPortrait"
//    )
//    public static class FoilSpecialArtSingleCardView {
//        private static ShaderProgram oldShader;
//        private static Color oldColor;
//
//        public static void Prefix(SingleCardViewPopup __instance, SpriteBatch sb) {
//            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
//            if (isFoil(card)) {
//                oldShader = sb.getShader();
//                sb.setShader(ART_SHADER);
//                ART_SHADER.setUniformf("shift_amt", FoilSpecialArt.shiftAmts.computeIfAbsent(card.cardID, key -> {
//                    Random rng = new Random((long) card.cardID.hashCode());
//                    return 0.2F + rng.random(0F, 0.6F);
//                }));
//            }
//        }
//
//        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
//            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
//            if (isFoil(card)) {
//                sb.setShader(oldShader);
//            }
//        }
//    }
//
//    @SpirePatch(
//            clz = AbstractCard.class,
//            method = "renderBack"
//    )
//    public static class FoilCardsSpecialCardbacks {
//        private static ShaderProgram oldShader;
//        private static Color oldColor;
//
//        public static void Prefix(AbstractCard __instance, SpriteBatch sb, boolean hovered, boolean selected) {
//            if (isFoil(__instance)) {
//                oldShader = sb.getShader();
//                sb.setShader(shade);
//                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
//                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", hslcCardBacks);
//            }
//        }
//
//        public static void Postfix(AbstractCard __instance, SpriteBatch sb, boolean hovered, boolean selected) {
//            if (isFoil(__instance)) {
//                sb.setShader(oldShader);
//                sb.setColor(oldColor);
//            }
//        }
//    }

    private static void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY, float drawScale, float angle) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, drawScale * Settings.scale, drawScale * Settings.scale, angle);
    }
}
