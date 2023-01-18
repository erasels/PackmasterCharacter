package thePackmaster.hats.specialhats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.vfx.psychicpack.SpacedRuneParticleEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PsychicHat implements SpecialHat {
    private static final float MIN_DELAY = 0.15f;
    private static final int EFFECT_NUM = 5;

    private final List<AbstractGameEffect> underEffects = new ArrayList<>(), overEffects = new ArrayList<>();
    private float underDelay = 0, overDelay = 0;

    @Override
    public void preRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y) {
        underDelay -= Gdx.graphics.getDeltaTime();
        if (underEffects.size() < EFFECT_NUM && underDelay <= 0) {
            underDelay = MIN_DELAY;
            underEffects.add(new SpacedRuneParticleEffect(x, y, Settings.scale * MathUtils.random(0.7f, 1.2f)));
        }
        overDelay -= Gdx.graphics.getDeltaTime();
        if (overEffects.size() < EFFECT_NUM && overDelay <= 0) {
            overDelay = MIN_DELAY;
            overEffects.add(new SpacedRuneParticleEffect(x, y, Settings.scale * MathUtils.random(0.6f, 1.0f)));
        }

        Iterator<AbstractGameEffect> effectIterator = underEffects.iterator();
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

    @Override
    public void postRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y) {
        Iterator<AbstractGameEffect> effectIterator = overEffects.iterator();
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
