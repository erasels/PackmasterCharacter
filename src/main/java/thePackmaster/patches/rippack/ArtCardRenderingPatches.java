package thePackmaster.patches.rippack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.cardManip.CardFlashVfx;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;

import static thePackmaster.SpireAnniversary5Mod.modID;
import static thePackmaster.util.Wiz.isArtCard;

//Houses patches specifically for rendering art half of cards
//Minus the initial shader application done in AllCardsRippablePatches
public class ArtCardRenderingPatches {

    private static final Texture ART_GLOW = ImageMaster.loadImage(modID + "Resources/images/512/rip/card_art.png");

    //Removes the flash from appearing where there is no card
    @SpirePatch(clz = CardFlashVfx.class, method = "render")
    public static class CutOffFlash {

        @SpirePrefixPatch()
        public static void Prefix(CardFlashVfx __instance, SpriteBatch sb, AbstractCard ___card, Color ___color, boolean ___isSuper) {
            if (isArtCard(___card)) {
                ReflectionHacks.setPrivate(__instance, CardFlashVfx.class, "img", new TextureAtlas.AtlasRegion(ART_GLOW, 0, 0, ART_GLOW.getWidth(), ART_GLOW.getHeight()));
            }
        }
    }

    //Removes the card glowing from appearing where there is no card
    @SpirePatch(clz = CardGlowBorder.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, Color.class})
    public static class CutOffGlow {

        @SpirePostfixPatch()
        public static void Postfix(CardGlowBorder __instance, AbstractCard ___card) {
            if (isArtCard(___card)) {
                ReflectionHacks.setPrivate(__instance, CardGlowBorder.class, "img", new TextureAtlas.AtlasRegion(ART_GLOW, 0, 0, ART_GLOW.getWidth(), ART_GLOW.getHeight()));
            }
        }
    }

    //Removes the card background shadow from appearing where there is no card
    @SpirePatch(clz = AbstractCard.class, method = "getCardBgAtlas")
    public static class CutOffCardBg {

        @SpirePrefixPatch()
        public static SpireReturn<TextureAtlas.AtlasRegion> Postfix(AbstractCard __instance) {
            if (isArtCard(__instance)) {
                return SpireReturn.Return(new TextureAtlas.AtlasRegion(ART_GLOW, 0, 0, ART_GLOW.getWidth(), ART_GLOW.getHeight()));
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderDescription")
    public static class SkipDescription {

        @SpirePrefixPatch()
        public static SpireReturn Postfix(AbstractCard __instance) {
            if (isArtCard(__instance)) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
