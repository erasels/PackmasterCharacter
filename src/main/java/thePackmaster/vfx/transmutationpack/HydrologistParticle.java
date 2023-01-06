package thePackmaster.vfx.transmutationpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class HydrologistParticle extends AbstractGameEffect {
    private static final Texture WATER_TEXTURE = new Texture("anniv5Resources/images/vfx/transmutationpack/WaterSprite.png");
    private static final TextureRegion[] WATER = new TextureRegion[8];
    private static final int ANIM_FPS = 60;
    private static final float FRAME_DURATION = 1.0f / ANIM_FPS;
    private final float x;
    private final float y;
    private final float rotation;
    private final float scale;
    private final float endDuration;
    private int index = 0;

    public HydrologistParticle(float x, float y, float rotation, float scale) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.scale = scale;
        endDuration = WATER.length * FRAME_DURATION;
        duration = 0;
    }

    public static void initializeRegions() {
        int wa = 0;
        for (int h = 0; h < 2; ++h) {
            for (int w = 0; w < 4; ++w) {
                WATER[wa] = new TextureRegion(WATER_TEXTURE, 72 * w, 72 * h, 72, 72);
                ++wa;
            }
        }
    }

    public void update() {
        duration += Gdx.graphics.getDeltaTime();
        index = (int)Math.floor((duration / endDuration) * WATER.length);
        if (index >= WATER.length) {
            isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        if (WATER[0] == null) {
            initializeRegions();
        }
        TextureRegion img = WATER[index];
        float w = img.getRegionWidth();
        float h = img.getRegionHeight();
        sb.draw(img, x - (w / 2.0f), y - (h / 2.0f), (w / 2.0f), (h / 2.0f), w, h, scale * Settings.scale, scale * Settings.scale, rotation);
    }

    public void dispose() {

    }
}
