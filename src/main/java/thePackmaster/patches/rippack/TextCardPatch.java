package thePackmaster.patches.rippack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.cardManip.CardFlashVfx;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;
import thePackmaster.cards.rippack.AbstractRippedTextCard;

import static thePackmaster.SpireAnniversary5Mod.modID;

public class TextCardPatch {

    private static ShaderProgram shader = AbstractRippedTextCard.shader;
    private static final Texture TEXT_GLOW = ImageMaster.loadImage(modID + "Resources/images/512/rip/card_text.png");

    //Completely skip rendering the portrait on text cards
    @SpirePatch(clz = AbstractCard.class, method = "renderPortrait")
    public static class SkipPortrait {

        @SpirePrefixPatch()
        public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (__instance instanceof AbstractRippedTextCard) {
                return SpireReturn.Return();
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    //This pair of patches removes the shader from rendering the description
    //A side effect of the shader causes some characters of the description to be removed
    @SpirePatch(clz = AbstractCard.class, method = "renderDescription")
    @SpirePatch(clz = AbstractCard.class, method = "renderDescriptionCN")
    @SpirePatch(clz = AbstractCard.class, method = "renderType")
    public static class ISeeTreesOfGreen {

        @SpirePrefixPatch()
        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (__instance instanceof AbstractRippedTextCard) {
                sb.setShader(null);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderDescription")
    @SpirePatch(clz = AbstractCard.class, method = "renderDescriptionCN")
    @SpirePatch(clz = AbstractCard.class, method = "renderType")
    public static class RedRosesToo {

        @SpirePostfixPatch()
        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            if (__instance instanceof AbstractRippedTextCard) {
                sb.setShader(shader);
            }
        }
    }

    //Removes the flash from appearing where there is no card
    @SpirePatch(clz = CardFlashVfx.class, method = "render")
    public static class CutOffFlash {

        @SpirePrefixPatch()
        public static void Prefix(CardFlashVfx __instance, SpriteBatch sb, AbstractCard ___card, Color ___color, boolean ___isSuper) {
            if (___card instanceof AbstractRippedTextCard) {
                ReflectionHacks.setPrivate(__instance, CardFlashVfx.class, "img", new TextureAtlas.AtlasRegion(TEXT_GLOW, 0, 0, TEXT_GLOW.getWidth(), TEXT_GLOW.getHeight()));
            }
        }
    }

    //Removes the card glowing from appearing where there is no card
    @SpirePatch(clz = CardGlowBorder.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, Color.class})
    public static class CutOffGlow {

        @SpirePostfixPatch()
        public static void Postfix(CardGlowBorder __instance, AbstractCard ___card) {
            if (___card instanceof AbstractRippedTextCard) {
                ReflectionHacks.setPrivate(__instance, CardGlowBorder.class, "img", new TextureAtlas.AtlasRegion(TEXT_GLOW, 0, 0, TEXT_GLOW.getWidth(), TEXT_GLOW.getHeight()));
            }
        }
    }

    //Removes the card background shadow from appearing where there is no card
    @SpirePatch(clz = AbstractCard.class, method = "getCardBgAtlas")
    public static class CutOffCardBg {

        @SpirePrefixPatch()
        public static SpireReturn<TextureAtlas.AtlasRegion> Postfix(AbstractCard __instance) {
            if (__instance instanceof AbstractRippedTextCard) {
                return SpireReturn.Return(new TextureAtlas.AtlasRegion(TEXT_GLOW, 0, 0, TEXT_GLOW.getWidth(), TEXT_GLOW.getHeight()));
            }
            return SpireReturn.Continue();
        }
    }
}
