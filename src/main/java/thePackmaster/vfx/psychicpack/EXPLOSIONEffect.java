package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class EXPLOSIONEffect extends AbstractGameEffect {
    private static final float PER_FRAME = 0.12f;
    private static final int FRAME_COUNT = 17;
    private static final int WIDTH = 71, HEIGHT = 100;
    private static final float X_OFF = WIDTH / 2f, Y_OFF = HEIGHT / 2f;
    private static final TextureAtlas.AtlasRegion[] frames = new TextureAtlas.AtlasRegion[FRAME_COUNT];
    static {
        Texture atlas = TexLoader.getTexture(makeImagePath("vfx/goodexplosion.png"));
        int columnCount = atlas.getWidth() / WIDTH;
        int rowCount = atlas.getHeight() / HEIGHT;

        int currentFrame = 0;
        for (int y = 0; y < rowCount; ++y)
        {
            for (int x = 0; x < columnCount; ++x)
            {
                frames[currentFrame] = new TextureAtlas.AtlasRegion(atlas, x * 250, y * 190, 250, 190);
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
    private int currentFrame = 0;

    public EXPLOSIONEffect(float x, float y) {
        this.x = x;
        this.y = y;
        this.color = Color.WHITE.cpy();
        frameTime = PER_FRAME;
    }

    @Override
    public void update() {
        frameTime -= Gdx.graphics.getDeltaTime();
        if (frameTime <= 0) {
            frameTime = PER_FRAME;
            ++currentFrame;
            if (currentFrame >= frames.length) {
                isDone = true;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (currentFrame < frames.length) {
            sb.setColor(color);
            sb.draw(frames[currentFrame], x - X_OFF, y - Y_OFF, X_OFF, Y_OFF, WIDTH, HEIGHT, Settings.scale, Settings.scale, 0);
        }
    }

    @Override
    public void dispose() {

    }
}
