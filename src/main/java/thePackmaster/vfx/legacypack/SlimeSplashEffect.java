package thePackmaster.vfx.legacypack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SlimeSplashEffect
        extends AbstractGameEffect
{
    private float x;
    private float y;
    private Texture img;
    private float scaleD;

    public SlimeSplashEffect(float x, float y)
    {
        this.duration = 1.0F;
        this.startingDuration = 1.0F;

        this.x = x;
        this.y = y;

        this.scaleD = 0.0F;

        int tmp = MathUtils.random(2);
        if (tmp == 0) {
            this.color = Color.CYAN.cpy();
        } else if (tmp == 1) {
            this.color = Color.SKY.cpy();
        } else {
            this.color = Color.GREEN.cpy();
        }

        tmp = MathUtils.random(2);
        if (tmp == 0) {
            this.img = ImageMaster.loadImage("anniv5Resources/images/vfx/slimeSplashA.png");
        } else if (tmp == 1) {
            this.img = ImageMaster.loadImage("anniv5Resources/images/vfx/slimeSplashB.png");
        } else {
            this.img = ImageMaster.loadImage("anniv5Resources/images/vfx/slimeSplashC.png");
        }

        this.scale = (MathUtils.random(0.3F, 0.5F) * Settings.scale);
    }

    public void update()
    {
        if (this.duration == 1.0F) {
            for (int i = 0; i < 20; i++) {
                AbstractDungeon.effectsQueue.add(new SlimeDropletEffect(this.x, this.y));
            }
        }
        Interpolation.swingOut.apply(this.scale, this.scaleD, this.duration);
        super.update();
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);

        sb.draw(this.img, this.x - this.img.getWidth() / 2.0F, this.y - this.img.getHeight() / 2.0F, this.img.getWidth() / 2.0F, this.img.getHeight() / 2.0F, this.img.getWidth(), this.img.getHeight(),
                this.scale, this.scale,
                this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
    }

    public void dispose() {
        img.dispose();
    }
}