package thePackmaster.vfx.rippack;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class DividedFuryEffect {
    public static Texture SPEAR = new Texture(modID + "Resources/images/vfx/rippack/lightningSpear.png");

    public static AbstractGameEffect LightningSpearThrow(AbstractCreature c, boolean muted) {

        AbstractPlayer p = AbstractDungeon.player;
        float angle = getAngle(c);
        Color color = new Color(1.0f, 1.0f, 120.0f / 255.0f, 1.0f);

        if(muted) {
            return new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + (p.hb.height / 2), 0.5f)
                    .setAngle(angle)
                    .setColor(color)
                    .scale(0.0f, 0.3f)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25f)
                                    .fadeOut(0.3f)
                                    .setX(x + MathUtils.random(-300.0f * Settings.scale, 300.0f * Settings.scale))
                                    .setY(y + MathUtils.random(-100.0f * Settings.scale, 100.0f * Settings.scale))
                                    .setScale(0.3f)
                                    .setColor(color)
                                    .build(), 0.01f)
                    .andThen(0.25f)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25f)
                                    .fadeOut(0.3f)
                                    .setX(x + MathUtils.random(-300.0f * Settings.scale, 300.0f * Settings.scale))
                                    .setY(y + MathUtils.random(-100.0f * Settings.scale, 100.0f * Settings.scale))
                                    .setScale(0.3f)
                                    .setColor(color)
                                    .build(), 0.01f)
                    .andThen(0.2f)
                    .setScale(0.3f)
                    .moveX(p.hb.cX, c.hb.cX)
                    .moveY(p.hb.cY + (p.hb.height / 2), c.hb.cY)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(SPEAR, x, y, 0.5f)
                                    .fadeOutFromAlpha(0.3f, 0.4f)
                                    .setScale(0.3f)
                                    .setAngle(angle)
                                    .setColor(color)
                                    .build(), 0.05f)
                    .build();
        } else {
            return new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + (p.hb.height / 2), 0.5f)
                    .playSoundAt(0.01f, makeID("RipPack_Charge"))
                    .setAngle(angle)
                    .setColor(color)
                    .scale(0.0f, 0.3f)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25f)
                                    .fadeOut(0.3f)
                                    .setX(x + MathUtils.random(-300.0f * Settings.scale, 300.0f * Settings.scale))
                                    .setY(y + MathUtils.random(-100.0f * Settings.scale, 100.0f * Settings.scale))
                                    .setScale(0.3f)
                                    .setColor(color)
                                    .build(), 0.01f)
                    .andThen(0.25f)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25f)
                                    .fadeOut(0.3f)
                                    .setX(x + MathUtils.random(-300.0f * Settings.scale, 300.0f * Settings.scale))
                                    .setY(y + MathUtils.random(-100.0f * Settings.scale, 100.0f * Settings.scale))
                                    .setScale(0.3f)
                                    .setColor(color)
                                    .build(), 0.01f)
                    .andThen(0.2f)
                    .playSoundAt(0.01f, makeID("RipPack_SpearThrow"))
                    .setScale(0.3f)
                    .moveX(p.hb.cX, c.hb.cX)
                    .moveY(p.hb.cY + (p.hb.height / 2), c.hb.cY)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(SPEAR, x, y, 0.5f)
                                    .fadeOutFromAlpha(0.3f, 0.4f)
                                    .setScale(0.3f)
                                    .setAngle(angle)
                                    .setColor(color)
                                    .build(), 0.05f)
                    .build();
        }
    }

    public static AbstractGameEffect LightningSpearThrowFast(AbstractCreature c, boolean muted) {
        AbstractPlayer p = AbstractDungeon.player;
        float angle = getAngle(c);
        Color color = new Color(1.0f, 1.0f, 120.0f / 255.0f, 1.0f);

        if(muted) {
            return new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + (p.hb.height / 2) , 0.25f)
                    .setAngle(angle)
                    .setScale(0.3f)
                    .moveX(p.hb.cX, c.hb.cX)
                    .moveY(p.hb.cY + (p.hb.height / 2), c.hb.cY)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(SPEAR, x, y, 0.5f)
                                    .fadeOutFromAlpha(0.3f, 0.4f)
                                    .setScale(0.3f)
                                    .setAngle(angle)
                                    .setColor(color)
                                    .build(), 0.05f)
                    .build();
        } else {
            return new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + (p.hb.height / 2), 0.25f)
                    .setAngle(angle)
                    .playSoundAt(0.01f, makeID("RipPack_SpearThrow"))
                    .setScale(0.3f)
                    .moveX(p.hb.cX, c.hb.cX)
                    .moveY(p.hb.cY + (p.hb.height / 2), c.hb.cY)
                    .emitEvery(
                            (x, y) -> new VfxBuilder(SPEAR, x, y, 0.5f)
                                    .fadeOutFromAlpha(0.3f, 0.4f)
                                    .setScale(0.3f)
                                    .setAngle(angle)
                                    .setColor(color)
                                    .build(), 0.05f)
                    .build();
        }
    }

    private static float getAngle(AbstractCreature c) {
        AbstractPlayer p = AbstractDungeon.player;

        float distance = c.hb.cX - p.hb.cX;
        float height = p.hb.cY + (p.hb.height / 2) - c.hb.cY;
        float angle = (float) Math.toDegrees(Math.atan(distance / height));

        //If the enemy hb is higher than the character's, the image gets flipped
        if(height < 0 ) {
            return angle + 180.0f;
        }
        return angle;
    }
}
