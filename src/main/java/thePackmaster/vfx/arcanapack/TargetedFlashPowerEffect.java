package thePackmaster.vfx.arcanapack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TargetedFlashPowerEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img;
    private TextureAtlas.AtlasRegion region128;
    private float scale;

    public TargetedFlashPowerEffect(AbstractPower power, AbstractCreature target) {
        this.scale = Settings.scale;
        if (!target.isDeadOrEscaped()) {
            this.x = target.hb.cX;
            this.y = target.hb.cY;
        }

        this.img = power.img;
        this.region128 = power.region128;
        if (this.img == null) {
            this.x -= (float)(this.region128.packedWidth / 2);
            this.y -= (float)(this.region128.packedHeight / 2);
        }

        this.duration = 0.7F;
        this.startingDuration = 0.7F;
        this.color = Color.WHITE.cpy();
        this.renderBehind = false;
    }

    public void update() {
        super.update();
        this.scale = Interpolation.exp5In.apply(Settings.scale, Settings.scale * 0.3F, this.duration / this.startingDuration);
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        if (this.img != null) {
            sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 12.0F, this.scale * 12.0F, 0.0F, 0, 0, 32, 32, false, false);
            sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 10.0F, this.scale * 10.0F, 0.0F, 0, 0, 32, 32, false, false);
            sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 8.0F, this.scale * 8.0F, 0.0F, 0, 0, 32, 32, false, false);
            sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 7.0F, this.scale * 7.0F, 0.0F, 0, 0, 32, 32, false, false);
        } else {
            sb.draw(this.region128, this.x, this.y, (float)this.region128.packedWidth / 2.0F, (float)this.region128.packedHeight / 2.0F, (float)this.region128.packedWidth, (float)this.region128.packedHeight, this.scale * 3.0F, this.scale * 3.0F, 0.0F);
        }

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
