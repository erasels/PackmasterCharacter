package thePackmaster.vfx.summonspack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.orbs.summonspack.Panda.GRAVITY;

public class QuillEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float rotation;
    private boolean sound;

    private static final String IMG_PATH = makePath("/images/orbs/summonsPack/Needle.png");
    private static final Texture IMG = TexLoader.getTexture(IMG_PATH);
    private static final float QUILL_WIDTH = 36f;

    public QuillEffect(float x, float y) {
        this.x = x;
        this.y = y;
        vX = MathUtils.random(-1000f, 1000f);
        vY = MathUtils.random(250f, 1200f);
        sound = false;
    }

    @Override
    public void update() {
        if (!sound) {
            CardCrawlGame.sound.play("RELIC_DROP_FLAT", 0.1F);
            sound = true;
        }
        float delta = Gdx.graphics.getDeltaTime();
        x += delta*vX;
        y += delta*vY - GRAVITY * delta * delta / 2f;
        vY -= delta * GRAVITY;
        rotation = (float) (Math.atan2(vY, vX) * 180f / Math.PI);
        if (y < 0 - QUILL_WIDTH)
            isDone = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 771);
        sb.setColor(Color.WHITE.cpy());
        sb.draw(IMG, x - QUILL_WIDTH /2F, y - QUILL_WIDTH /2F, QUILL_WIDTH /2F, QUILL_WIDTH /2F,
                QUILL_WIDTH, QUILL_WIDTH, scale, scale, rotation, 0, 0, (int) QUILL_WIDTH, (int) QUILL_WIDTH,
                false, false);
    }

    @Override
    public void dispose() {
    }
}
