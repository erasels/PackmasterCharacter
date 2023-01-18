package thePackmaster.vfx.dragonwrathpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ImpactSparkEffect;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class SmiteLightningEffect extends AbstractGameEffect {
    private float sX;
    private float sY;
    private float dX;
    private float dY;
    private float dst;
    private static final float DUR = 0.5F;
    private static TextureAtlas.AtlasRegion img;
    private static final Texture LIGHTNINGBOLT = ImageMaster.loadImage(makeImagePath("vfx/lightningspear.png"));
    public SmiteLightningEffect(float sX, float sY, float dX, float dY) {
        if (img == null) {
            img = new TextureAtlas.AtlasRegion(LIGHTNINGBOLT,0,0,LIGHTNINGBOLT.getWidth(),LIGHTNINGBOLT.getHeight());
        }

        this.sX = sX;
        this.sY = sY;
        this.dX = dX;
        this.dY = dY;
        this.dst = Vector2.dst(this.sX, this.sY, this.dX, this.dY) / Settings.scale;
        this.color = Color.GOLD.cpy();
        this.duration = 0.2F;
        this.startingDuration = 0.2F;
        this.rotation = MathUtils.atan2(dX - sX, dY - sY);
        this.rotation *= 57.295776F;
        this.rotation = -this.rotation + 90.0F;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (this.duration - 0.25F) * 4.0F);
        } else {
            this.color.a = Interpolation.bounceIn.apply(0.0F, 1.0F, this.duration * 4.0F);
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(img, this.sX, this.sY - (float)img.packedHeight / 2.0F + 10.0F * Settings.scale, 0.0F, (float)img.packedHeight / 2.0F, this.dst, img.packedHeight, this.scale + MathUtils.random(-0.01F, 0.01F), this.scale, this.rotation);
        for(int i = 0; i < 8; ++i) {
            AbstractDungeon.topLevelEffectsQueue.add(new ImpactSparkEffect(this.sX + MathUtils.random(-20.0F, 20.0F) * Settings.scale, this.sY + MathUtils.random(-20.0F, 20.0F) * Settings.scale));
        }
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
