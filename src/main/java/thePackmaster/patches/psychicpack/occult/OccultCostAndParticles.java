package thePackmaster.patches.psychicpack.occult;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import javassist.CtBehavior;
import thePackmaster.vfx.psychicpack.RuneParticleEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class OccultCostAndParticles {
    private static final Color occultCostColor = new Color(0.7f, 0.3f, 1.0f, 1.0f);

    //change number color to purple. rune particles emanate off of energy orb.

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class Fields {
        public static SpireField<ArrayList<AbstractGameEffect>> runeEffects = new SpireField<>(ArrayList::new);
        public static SpireField<Float> runeTimer = new SpireField<>(()->0.0f);
    }

    //Patch into the methods updateGlow and postfix renderEnergy
    @SpirePatch(
            clz = AbstractCard.class,
            method = "updateGlow"
    )
    public static class RuneEffects {
        @SpirePrefixPatch
        public static void updateRunes(AbstractCard __instance)
        {
            if (__instance.isGlowing && OccultFields.isOccult.get(__instance))
            {
                Iterator<AbstractGameEffect> runeIterator = Fields.runeEffects.get(__instance).iterator();

                while (runeIterator.hasNext())
                {
                    AbstractGameEffect e = runeIterator.next();
                    e.update();
                    if (e.isDone)
                        runeIterator.remove();
                }

                float f = Fields.runeTimer.get(__instance) - Gdx.graphics.getDeltaTime();
                if (f <= 0)
                {
                    Vector2 p = new Vector2(-132.0F * __instance.drawScale * Settings.scale, 192.0F * __instance.drawScale * Settings.scale);

                    rotatePoint(p, __instance.angle);

                    Fields.runeEffects.get(__instance).add(new RuneParticleEffect(
                            __instance.current_x + p.x,
                            __instance.current_y + p.y,
                            __instance.drawScale));

                    f = MathUtils.random(0.45f, 0.8f);
                }
                Fields.runeTimer.set(__instance, f);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderEnergy"
    )
    public static class EnergyColorAndRuneRendering {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "costColor" }
        )
        public static void adjustColor(AbstractCard __instance, SpriteBatch sb, @ByRef Color[] costColor)
        {
            if (OccultFields.isOccult.get(__instance))
            {
                costColor[0] = occultCostColor;
            }

            for (AbstractGameEffect e : Fields.runeEffects.get(__instance))
            {
                e.render(sb);
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "transparency");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    //rotate vector p around 0, 0 by angle in degrees
    private static Vector2 rotatePoint(Vector2 p, float angle)
    {
        float s = MathUtils.sin(MathUtils.degreesToRadians * angle);
        float c = MathUtils.cos(MathUtils.degreesToRadians * angle);

        p.x = p.x * c - p.y * s;
        p.y = p.x * s + p.y * c;

        return p;
    }
}
