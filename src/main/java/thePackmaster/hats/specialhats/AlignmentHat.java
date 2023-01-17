package thePackmaster.hats.specialhats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ShineSparkleEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AlignmentHat implements SpecialHat {
    private static final int EFFECT_NUM = 5;

    private final List<AbstractGameEffect> underEffects = new ArrayList<>(), overEffects = new ArrayList<>();

    @Override
    public void preRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y) {
        if (underEffects.size() < EFFECT_NUM) {
            underEffects.add(new ShineSparkleEffect(x, y));
        }
        if (overEffects.size() < EFFECT_NUM) {
            overEffects.add(new ShineSparkleEffect(x, y));
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
