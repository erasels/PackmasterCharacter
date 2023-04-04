package thePackmaster.vfx.instadeathpack;

import basemod.Pair;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;

import java.util.ArrayList;
import java.util.List;

public class ReversingSlashEffect extends AnimatedSlashEffect {
    private Color color2;
    public boolean end = false;
    private int flips = 0;
    private List<Pair<Color, Color>> queuedColors = new ArrayList<>();
    private boolean reachedEnd = false;

    private final float ix, iy;
    private float dx, dy;

    public ReversingSlashEffect(float x, float y, float dX, float dY, float angle, Color color1, Color color2) {
        super(x, y, dX, dY, angle, color1, color2);
        this.ix = x;
        this.iy = y;
        this.dx = dX;
        this.dy = dY;
        this.color2 = color2;
    }

    public ReversingSlashEffect(float x, float y, float dX, float dY, float angle, float targetScale, Color color1, Color color2) {
        super(x, y, dX, dY, angle, targetScale, color1, color2);
        this.ix = x;
        this.iy = y;
        this.dx = dX;
        this.dy = dY;
        this.color2 = color2;
    }

    public void reverse() {
        reverse(color, color2);
    }

    public void reverse(Color color1, Color color2) {
        ++flips;
        queuedColors.add(new Pair<>(color1, color2));
    }

    public void update() {
        if (reachedEnd) {
            if (end) {
                isDone = true;
                return;
            }
            else if (flips <= 0) {
                color.a = Math.max(0, color.a - Gdx.graphics.getDeltaTime() * 4);
                color2.a = Math.max(0, color2.a - Gdx.graphics.getDeltaTime() * 3);
                return;
            }

            flip();
        }

        super.update();
        isDone = false;
        if (duration < 0) {
            reachedEnd = true;
            duration = 0;
        }
    }

    private void flip() {
        --flips;

        dx *= -1;
        dy *= -1;

        float x = ix - 64.0F - dx / 2.0F * Settings.scale;
        float y = iy - 64.0F - dy / 2.0F * Settings.scale;

        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "x", x);
        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "y", y);
        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "sX", x);
        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "sY", y);
        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "tX", x + dx / 2.0F * Settings.scale);
        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "tY", y + dy / 2.0F * Settings.scale);

        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "scaleX", 0.01F);
        ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "scaleY", 0.01F);

        color.a = 0;
        color2.a = 1;
        this.duration = this.startingDuration;

        if (this.rotation < 360)
            this.rotation += 180;
        else
            this.rotation -= 180;

        reachedEnd = false;
        if (!queuedColors.isEmpty()) {
            Pair<Color, Color> next = queuedColors.remove(0);
            if (next.getKey() != null && next.getValue() != null) {
                next.getKey().a = color.a;
                color = next.getKey().cpy();
                color2 = next.getValue();
                color2.a = 1;
                ReflectionHacks.setPrivate(this, AnimatedSlashEffect.class, "color2", next.getValue().cpy());
            }
        }
    }
}
