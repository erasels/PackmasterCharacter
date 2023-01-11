package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class ExplodingPoleEffect extends AbstractGameEffect {
    private static final Texture pole = TexLoader.getTexture(makeImagePath("vfx/TelephonePole.png"));
    private static final float X_OFF = pole.getWidth() / 2f;
    private static final float Y_OFF = pole.getHeight() / 2f;


    private final float tx, ty, ix, iy;
    float x, y;

    boolean exploded = false;

    public ExplodingPoleEffect(AbstractCreature target, AbstractCreature source) {
        tx = target.hb.cX;
        ty = target.hb.cY;

        iy = Settings.HEIGHT + 400;

        float dy = iy - ty;
        float dx;
        float angle;

        if (target.hb.cX < source.hb.cX) {
            rotation = MathUtils.random(120f, 140f);
            angle = 180 - rotation;
            dx = -MathUtils.cosDeg(angle) * dy;
        }
        else {
            rotation = -MathUtils.random(120f, 140f);
            angle = 180 + rotation;
            dx = MathUtils.cosDeg(angle) * dy;
        }

        ix = tx - dx;

        duration = startingDuration = 0.5f;
        color = Color.WHITE.cpy();

        x = ix;
        y = iy;
    }

    private static final float EXPLOSION_RANGE = 150f * Settings.scale;
    @Override
    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        if (duration <= 0.12f && !exploded) {
            exploded = true;

            int explosions = MathUtils.random(4, 7);
            for (int i = 0; i < explosions; ++i)
                AbstractDungeon.effectsQueue.add(new EXPLOSIONEffect(tx + MathUtils.random(-EXPLOSION_RANGE, EXPLOSION_RANGE), ty + MathUtils.random(-EXPLOSION_RANGE, EXPLOSION_RANGE), 0.12f));
        }
        if (duration <= 0) {
            duration = 0;
            this.isDone = true;
        }
        float prog = 1 - (duration / startingDuration);
        x = MathUtils.lerp(ix, tx, prog);
        y = MathUtils.lerp(iy, ty, prog);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(pole, x - X_OFF, y - Y_OFF, X_OFF, Y_OFF, pole.getWidth(), pole.getHeight(), Settings.scale, Settings.scale, rotation, 0, 0, pole.getWidth(), pole.getHeight(), false, false);
    }

    @Override
    public void dispose() {

    }
}
