package thePackmaster.hats.specialhats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.vfx.instadeathpack.HaloEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstantDeathHat implements SpecialHat {
    private static final float DELAY = 0.1f;
    private static final Color c = Color.valueOf("f5e07a");

    private final List<AbstractGameEffect> effects = new ArrayList<>();
    private float delay = 0;

    @Override
    public void preRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y) {
        delay -= Gdx.graphics.getDeltaTime();
        if (delay <= 0) {
            delay = DELAY;
            effects.add(new HaloEffect(x, y + 70 * Settings.scale, c));
        }
    }

    @Override
    public void postRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y) {
        Iterator<AbstractGameEffect> effectIterator = effects.iterator();
        AbstractGameEffect e;
        while (effectIterator.hasNext()) {
            e = effectIterator.next();
            e.update();
            if (e.isDone) {
                effectIterator.remove();
                continue;
            }

            e.render(sb);
        }
    }
}
