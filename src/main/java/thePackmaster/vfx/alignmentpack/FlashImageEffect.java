package thePackmaster.vfx.alignmentpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FlashImageEffect extends AbstractGameEffect {
    private final TextureRegion img;
    private final float xOff, yOff, x, y, initialScale;

    public FlashImageEffect(TextureRegion textureRegion) {
        this(textureRegion, Settings.WIDTH / 2f, Settings.HEIGHT / 2f, 1, Color.WHITE);
    }
    public FlashImageEffect(TextureRegion textureRegion, float x, float y, float scale, Color c) {
        this.img = textureRegion;
        this.xOff = img.getRegionWidth() / 2f;
        this.yOff = img.getRegionHeight() / 2f;
        this.x = x - xOff;
        this.y = y - yOff;

        this.scale = this.initialScale = scale;
        this.color = c.cpy();

        this.duration = this.startingDuration = 0.6F;
    }

    @Override
    public void update() {
        super.update();
        this.scale = this.initialScale * this.color.a;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, xOff, yOff, img.getRegionWidth(), img.getRegionHeight(), this.scale * Settings.scale, this.scale * Settings.scale, this.rotation);
    }

    @Override
    public void dispose() {

    }
}
