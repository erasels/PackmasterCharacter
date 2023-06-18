package thePackmaster.patches.overwhelmingpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class ZOOMAnimationPatch {
    @SpireEnum
    public static AbstractCreature.CreatureAnimation ZOOM;

    public static void useZOOMAnimation(AbstractCreature c) {
        ReflectionHacks.setPrivate(c, AbstractCreature.class, "animation", ZOOM);
        ReflectionHacks.setPrivate(c, AbstractCreature.class, "animationTimer", 0.5f);
    }

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "updateAnimations"
    )
    public static class UpdateZoomAnimation {
        @SpirePrefixPatch
        public static void WHEEEEEEE(AbstractCreature __instance, @ByRef float[] ___animationTimer, AbstractCreature.CreatureAnimation ___animation) {
            if (___animation == ZOOM && ___animationTimer[0] > 0) {
                ___animationTimer[0] -= Gdx.graphics.getDeltaTime();
                float targetPos;
                if (___animationTimer[0] >= 0.25f) {
                    targetPos = Settings.WIDTH * 2;
                    if (!__instance.isPlayer)
                        targetPos *= -1;
                    targetPos += (Settings.WIDTH / 2f);
                }
                else {
                    targetPos = -Settings.WIDTH / 4f;
                    if (!__instance.isPlayer) {
                        targetPos *= -1;
                        targetPos += Settings.WIDTH;
                    }
                }

                if (___animationTimer[0] >= 0.25f) {
                    __instance.animX = Interpolation.exp5In.apply(0f, targetPos, 1 - ((___animationTimer[0] - 0.25f) * 4));
                }
                else if (___animationTimer[0] <= 0.0f) {
                    ___animationTimer[0] = 0.0f;
                    __instance.animX = 0.0f;
                }
                else if (___animationTimer[0] <= 0.2f) {
                    __instance.animX = Interpolation.fade.apply(0f, targetPos, ___animationTimer[0] / 0.2f);
                }
            }
        }
    }
}
