package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class EXPLOSIONEffect extends AbstractGameEffect {
    private static final float PER_FRAME = 0.04f;
    private static final int FRAME_COUNT = 17;
    private static final int WIDTH = 71, HEIGHT = 100;
    private static final float X_OFF = WIDTH / 2f, Y_OFF = HEIGHT / 2f;
    private static final TextureAtlas.AtlasRegion[] frames = new TextureAtlas.AtlasRegion[FRAME_COUNT];
    static {
        Texture atlas = ImageMaster.loadImage(makeImagePath("vfx/goodexplosion.png"));
        int columnCount = atlas.getWidth() / WIDTH;
        int rowCount = atlas.getHeight() / HEIGHT;

        int currentFrame = 0;
        for (int y = 0; y < rowCount; ++y)
        {
            for (int x = 0; x < columnCount; ++x)
            {
                frames[currentFrame] = new TextureAtlas.AtlasRegion(atlas, x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
                currentFrame++;

                if (currentFrame >= FRAME_COUNT)
                {
                    break;
                }
            }
            if (currentFrame == FRAME_COUNT)
            {
                break;
            }
        }
    }

    private final float x, y;
    private float frameTime;
    private int currentFrame = -1;

    private final float frameRate = PER_FRAME * MathUtils.random(0.9f, 1.4f);

    public EXPLOSIONEffect(float x, float y, float maxDelay) {
        this.x = x;
        this.y = y;
        this.color = Color.WHITE.cpy();
        frameTime = frameRate + MathUtils.random(maxDelay);
    }

    @Override
    public void update() {
        frameTime -= Gdx.graphics.getDeltaTime();
        if (frameTime <= 0) {
            frameTime += frameRate;
            ++currentFrame;
            if (currentFrame >= frames.length) {
                isDone = true;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (currentFrame >= 0 && currentFrame < frames.length) {
            sb.setColor(color);
            sb.draw(frames[currentFrame], x - X_OFF, y - Y_OFF, X_OFF, Y_OFF, WIDTH, HEIGHT, Settings.scale * 4, Settings.scale * 4, 0);
        }
    }

    @Override
    public void dispose() {

    }
}
