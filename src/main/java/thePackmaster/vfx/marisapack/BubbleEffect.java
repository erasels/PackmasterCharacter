package thePackmaster.vfx.marisapack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BubbleEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private TextureAtlas.AtlasRegion img;
    private String soundKey;

    public BubbleEffect(Color c, String soundKey) {
        this(c, soundKey, AbstractDungeon.player.hb.cX - ImageMaster.CRYSTAL_IMPACT.packedWidth / 2.0F, AbstractDungeon.player.hb.cY - ImageMaster.CRYSTAL_IMPACT.packedHeight / 2.0F);
    }

    public BubbleEffect(Color c, String soundKey, float y) {
        this(c, soundKey, (AbstractDungeon.player.hb.cX - ImageMaster.CRYSTAL_IMPACT.packedWidth / 2.0F), y);
    }

    public BubbleEffect(Color c, String soundKey, float x, float y) {
        this.img = ImageMaster.CRYSTAL_IMPACT;
        this.x = x;
        this.y = y;

        this.startingDuration = 1.5F;
        this.duration = this.startingDuration;
        this.scale = Settings.scale;
        this.color = c.cpy();
        this.renderBehind = true;
        this.soundKey = soundKey;
    }

    public void update() {
        if (this.duration == this.startingDuration && soundKey != null && !soundKey.isEmpty()) {
            CardCrawlGame.sound.play(soundKey, 0f);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = (Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale);
        } else {
            this.color.a = (Interpolation.fade.apply(0.01F, 1.0F, this.duration / (this.startingDuration / 2.0F)) * Settings.scale);
        }
        this.scale = (Interpolation.elasticIn.apply(4.0F, 0.01F, this.duration / this.startingDuration) * Settings.scale);
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.15F, this.scale * 1.15F, 0.0F);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, 0.0F);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.85F, this.scale * 0.85F, 0.0F);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.7F, this.scale * 0.7F, 0.0F);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
