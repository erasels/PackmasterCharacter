package thePackmaster.vfx.marisapack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MissileStrikeEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float destY;
    private static final float DUR = 0.4F;
    private TextureAtlas.AtlasRegion img;
    private boolean playedSound = false;
    private boolean forcedAngle = false;
    private String soundKey;

    public MissileStrikeEffect(float x, float y, Color col, String soundKey) {
        this.img = ImageMaster.DAGGER_STREAK;
        this.x = (x - MathUtils.random(320.0F, 360.0F) - this.img.packedWidth / 2.0F);
        this.destY = y;
        this.y = (this.destY + MathUtils.random(-25.0F, 25.0F) * Settings.scale - this.img.packedHeight / 2.0F);
        this.startingDuration = DUR;
        this.duration = DUR;
        this.scale = Settings.scale;
        this.rotation = MathUtils.random(-3.0F, 3.0F);
        this.color = col.cpy();
        this.soundKey = soundKey;
    }

    public MissileStrikeEffect(float x, float y, Color col) {
        this(x, y, col, "CARD_EXHAUST");
    }

    public MissileStrikeEffect(float x, float y, float fAngle, Color col)
    {
        this.img = ImageMaster.DAGGER_STREAK;
        this.x = (x - MathUtils.random(320.0F, 360.0F) - this.img.packedWidth / 2.0F);
        this.destY = y;
        this.y = (this.destY + MathUtils.random(-25.0F, 25.0F) * Settings.scale - this.img.packedHeight / 2.0F);
        this.startingDuration = DUR;
        this.duration = DUR;
        this.scale = Settings.scale;
        this.rotation = fAngle;
        this.color = col.cpy();
        this.forcedAngle = true;
        this.soundKey = null;
        this.playedSound = true;
    }

    public void update()
    {
        if (!this.playedSound)
        {
            CardCrawlGame.sound.playA(soundKey, 1F);
            this.playedSound = true;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
        if (this.duration > 0.2F) {
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.2F) * 5.0F);
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 5.0F);
        }
        this.scale = Interpolation.bounceIn.apply(Settings.scale * 0.5F, Settings.scale * 1.5F, this.duration / 0.4F);
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        if (!this.forcedAngle)
        {
            sb.draw(this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);

            sb.setBlendFunction(770, 1);
            sb.draw(this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);

            sb.setBlendFunction(770, 771);
        }
        else
        {
            sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);

            sb.setBlendFunction(770, 1);
            sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);

            sb.setBlendFunction(770, 771);
        }
    }

    public void dispose() {}
}
