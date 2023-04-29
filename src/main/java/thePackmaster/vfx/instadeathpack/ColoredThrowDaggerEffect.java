package thePackmaster.vfx.instadeathpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

public class ColoredThrowDaggerEffect extends ThrowDaggerEffect {
    public ColoredThrowDaggerEffect(float x, float y, Color c) {
        super(x, y);
        color = c.cpy();
    }

    public ColoredThrowDaggerEffect(float x, float y, float fAngle, Color c) {
        super(x, y, fAngle);
        color = c.cpy();
    }
}
