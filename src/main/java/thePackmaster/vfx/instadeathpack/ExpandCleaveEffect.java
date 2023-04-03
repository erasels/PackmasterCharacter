package thePackmaster.vfx.instadeathpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ExpandCleaveEffect extends AbstractGameEffect {
    private TextureAtlas.AtlasRegion img;
    private final float x, y, tA, explodePoint, fadeTime, endTime;

    private float xScale;
    private boolean playedSfx = false;

    public ExpandCleaveEffect(AbstractCreature src, Color color) {
        this.img = ImageMaster.vfxAtlas.findRegion("combat/cleave");
        this.color = color.cpy();
        this.tA = color.a;
        this.color.a = 0;
        this.x = src.hb.cX - (float)this.img.packedWidth / 2.0F;
        this.y = ((src.hb.cY + AbstractDungeon.floorY) / 2f) - (float)this.img.packedHeight / 2.0F;
        this.scale = xScale = 0.01F * Settings.scale;
        this.rotation = MathUtils.random(-1.0F, 1.0F);

        this.duration = 0;
        this.endTime = Settings.FAST_MODE ? 0.45f : 0.8f;
        this.explodePoint = Settings.FAST_MODE ? 0.2f : 0.3f;
        this.fadeTime = Settings.FAST_MODE ? 0.4f : 0.6f;
    }

    @Override
    public void update() {
        this.duration += Gdx.graphics.getDeltaTime();
        if (this.duration < explodePoint) {
            float prog = duration / explodePoint;
            this.color.a = Interpolation.circleIn.apply(0, tA, prog);
            this.scale = MathUtils.lerp(0.01f, 0.5f, prog) * Settings.scale;
            this.xScale = MathUtils.lerp(0.01f, 0.1f, prog) * Settings.scale;
        }
        else {
            float prog = (duration - explodePoint) / (endTime - explodePoint);
            if (this.duration > fadeTime) {
                if (!playedSfx) {
                    playedSfx = true;
                    playRandomSfX();
                    playRandomSfX();
                }
                float fadeProg = (duration - fadeTime) / (endTime - fadeTime);
                this.color.a = MathUtils.lerp(tA, 0, fadeProg);
                this.scale = MathUtils.lerp(0.8f, 0.34f, fadeProg) * Settings.scale;
            }
            else {
                this.color.a = tA;
                this.scale = MathUtils.lerp(0.5f, 0.8f, prog) * Settings.scale;
            }
            this.xScale = Interpolation.linear.apply(0.1f, 4f, prog) * Settings.scale;
        }

        if (duration > endTime)
            isDone = true;
    }

    private void playRandomSfX() {
        int roll = MathUtils.random(5);
        switch (roll) {
            case 0:
                CardCrawlGame.sound.playA("ATTACK_DAGGER_1", MathUtils.random(0.15f, 0.3f));
                break;
            case 1:
                CardCrawlGame.sound.play("ATTACK_DAGGER_2", MathUtils.random(0.15f, 0.3f));
                break;
            case 2:
                CardCrawlGame.sound.play("ATTACK_DAGGER_3", MathUtils.random(0.15f, 0.3f));
                break;
            case 3:
                CardCrawlGame.sound.play("ATTACK_DAGGER_4", MathUtils.random(0.15f, 0.3f));
                break;
            case 4:
                CardCrawlGame.sound.play("ATTACK_DAGGER_5", MathUtils.random(0.15f, 0.3f));
                break;
            default:
                CardCrawlGame.sound.play("ATTACK_DAGGER_6", MathUtils.random(0.15f, 0.3f));
        }

    }// 64

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.xScale, this.scale, this.rotation);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.xScale * 1.2f, this.scale * 1.2f, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() {

    }
}
