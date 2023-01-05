package thePackmaster.vfx.aggressionpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AggressionStanceAuraEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vY;
    private final AtlasRegion img;
    public static boolean switcher = true;

    public AggressionStanceAuraEffect() {
        this.img = ImageMaster.EXHAUST_L;
        this.duration = 2.0F;
        this.scale = MathUtils.random(2.7F, 2.5F) * Settings.scale;
        this.color = new Color(MathUtils.random(0.25F, 0.3F), MathUtils.random(0.0F, 0.05F), MathUtils.random(0.0F, 0.05F), 0.0F);
        this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 16.0F, AbstractDungeon.player.hb.width / 16.0F);
        this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 16.0F, AbstractDungeon.player.hb.height / 12.0F);
        this.x -= (float)this.img.packedWidth / 2.0F;
        this.y -= (float)this.img.packedHeight / 2.0F;
        switcher = !switcher;
        this.rotation = MathUtils.random(360.0F);
        if (switcher) {
            this.renderBehind = true;
            this.vY = MathUtils.random(0.0F, 40.0F);
        } else {
            this.renderBehind = false;
            this.vY = MathUtils.random(0.0F, -40.0F);
        }
    }

    public void update() {
        if (this.duration > 1.0F) {
            this.color.a = Interpolation.fade.apply(0.3F, 0.0F, this.duration - 1.0F);
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 0.3F, this.duration);
        }

        this.rotation += Gdx.graphics.getDeltaTime() * this.vY;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
