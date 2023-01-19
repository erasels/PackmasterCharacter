package thePackmaster.patches.summonpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import javassist.CtBehavior;
import thePackmaster.powers.boardgamepack.DicePower;

public class LeprechaunPatch {
    @SpirePatch(
            clz = GainPowerEffect.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class LoopDeLoop {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(GainPowerEffect __instance, AbstractPower power) {
            if (power instanceof DicePower && !((DicePower) power).sound) {
                ReflectionHacks.setPrivate(__instance, GainPowerEffect.class, "scale", Settings.scale);
                ReflectionHacks.setPrivate(__instance, AbstractGameEffect.class, "color",
                        new Color(1.0F, 1.0F, 1.0F, 0.5F));
                return SpireReturn.Return();
            }
            else
                return SpireReturn.Continue();
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractPower.class, "playApplyPowerSfx");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}