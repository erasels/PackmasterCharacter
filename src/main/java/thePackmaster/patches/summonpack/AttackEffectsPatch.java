package thePackmaster.patches.summonpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

public class AttackEffectsPatch {
    @SpirePatch2(
            clz = FlashAtkImgEffect.class,
            method = "loadImage"
    )
    public static class VfxPatch {
        public static SpireReturn<TextureAtlas.AtlasRegion> Prefix(FlashAtkImgEffect __instance) {
            AbstractGameAction.AttackEffect effect = ReflectionHacks.getPrivate(__instance, FlashAtkImgEffect.class, "effect");
            TextureAtlas.AtlasRegion output = getImage(effect);
            if (output == null)
                return SpireReturn.Continue();
            return SpireReturn.Return(output);
        }
    }

    public static TextureAtlas.AtlasRegion getImage (AbstractGameAction.AttackEffect effect) {
        Texture texture;

        if (effect == SpireAnniversary5Mod.Enums.EVIL)
            texture = TexLoader.getTexture(SpireAnniversary5Mod.EVIL_EFFECT_FILE);
        else
            return null;

        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    @SpirePatch2(
            clz = FlashAtkImgEffect.class,
            method = "playSound"
    )
    public static class SfxPatch {
        public static SpireReturn Prefix(AbstractGameAction.AttackEffect effect) {
            if (effect == SpireAnniversary5Mod.Enums.EVIL) {
                CardCrawlGame.sound.play(SpireAnniversary5Mod.EVIL_KEY);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}