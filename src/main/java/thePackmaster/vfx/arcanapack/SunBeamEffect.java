package thePackmaster.vfx.arcanapack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class SunBeamEffect extends AbstractGameEffect {
    private static final Texture sun = TexLoader.getTexture(makeImagePath("vfx/sunvfx.png"));
    private static final int WIDTH = 128, XOFF = 64;

    float x, xScale = 0;
    int step = 0;

    public SunBeamEffect(AbstractCreature target) {
        duration = 0.12f;
        x = target.hb.cX;

        color = Color.WHITE.cpy();
        color.a = 1;
    }

    @Override
    public void update() {
        switch (step) {
            case 0:
                duration -= Gdx.graphics.getDeltaTime();
                xScale = MathUtils.lerp(1, 0, duration / 0.12f);
                if (duration <= 0) {
                    xScale = 1;
                    step = 1;
                    duration = 0.1f;
                }
                break;
            case 1:
                duration -= Gdx.graphics.getDeltaTime();
                if (duration <= 0) {
                    step = 2;
                    duration = 0.1f;
                }
                break;
            default:
                duration -= Gdx.graphics.getDeltaTime();
                color.a = MathUtils.lerp(0, 1, duration / 0.1f);
                if (duration <= 0) {
                    color.a = 0;
                    isDone = true;
                }
                break;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(sun, x - XOFF, 0, XOFF, 0, WIDTH, Settings.HEIGHT, xScale * Settings.scale, 1, 0, 0, 0, 128, 128, false, false);
    }

    @Override
    public void dispose() {

    }
}
