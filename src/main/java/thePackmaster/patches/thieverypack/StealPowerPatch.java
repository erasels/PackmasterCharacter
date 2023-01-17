package thePackmaster.patches.thieverypack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.actions.thieverypack.StealPowerAction;

public class StealPowerPatch {
	static final float nullMagicNumber = -3434.228f;
	static float origAlpha = nullMagicNumber;
	static final int nullOrigAmount = -1628;
	static int origAmount = nullOrigAmount;

	@SpirePatch2(clz = AbstractPower.class, method = "renderIcons")
	public static class RenderIconPatch {
		@SpirePrefixPatch
		public static void Prefix(AbstractPower __instance, SpriteBatch sb, @ByRef float[] x, @ByRef float[] y, Color c) {
			if (StealPowerAction.activatedInstance != null && StealPowerAction.activatedInstance.affectedPowers.contains(__instance)) {
				origAlpha = c.a;
				if (StealPowerAction.activatedInstance.amount > 0) {
					if (__instance.amount > StealPowerAction.activatedInstance.amount) {
						renderIcons(__instance, sb, x[0], y[0], c);
					}
				}
				StealPowerAction.activatedInstance.calcPosition(x, y, c, false);
			}
		}

		private static void renderIcons(AbstractPower instance, SpriteBatch sb, float x, float y, Color c) {
			sb.setColor(c);
			if (instance.img != null) {
				sb.draw(instance.img, x - 12.0F, y - 12.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 32, 32, false, false);
			} else if (Settings.isMobile) {
				sb.draw(instance.region48, x - instance.region48.packedWidth / 2.0F, y - instance.region48.packedHeight / 2.0F, instance.region48.packedWidth / 2.0F, instance.region48.packedHeight / 2.0F, instance.region48.packedWidth, instance.region48.packedHeight, Settings.scale * 1.17F, Settings.scale * 1.17F, 0.0F);
			} else {
				sb.draw(instance.region48, x - instance.region48.packedWidth / 2.0F, y - instance.region48.packedHeight / 2.0F, instance.region48.packedWidth / 2.0F, instance.region48.packedHeight / 2.0F, instance.region48.packedWidth, instance.region48.packedHeight, Settings.scale, Settings.scale, 0.0F);
			}
		}

		@SpirePostfixPatch
		public static void Postfix(Color c) {
			if (origAlpha != nullMagicNumber) {
				// revert color
				c.a = origAlpha;
				origAlpha = nullMagicNumber;
			}
		}
	}

	@SpirePatch2(clz = AbstractPower.class, method = "renderAmount")
	public static class RenderAmountPatch {
		@SpirePrefixPatch
		public static void Prefix(AbstractPower __instance, SpriteBatch sb, @ByRef float[] x, @ByRef float[] y, Color c, float ___fontScale) {
			if (StealPowerAction.activatedInstance != null && StealPowerAction.activatedInstance.affectedPowers.contains(__instance)) {
				origAlpha = c.a;
				if (StealPowerAction.activatedInstance.amount > 0) {
					int amountLeft = __instance.amount - StealPowerAction.activatedInstance.amount;
					if (amountLeft > 0) {
						origAmount = __instance.amount;
						FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amountLeft), x[0], y[0], ___fontScale, c);
						__instance.amount = StealPowerAction.activatedInstance.amount;
					}
				}
				StealPowerAction.activatedInstance.calcPosition(x, y, c, true);
			}
		}

		@SpirePostfixPatch
		public static void Postfix(AbstractPower __instance, Color c) {
			if (origAlpha != nullMagicNumber) {
				// revert color
				c.a = origAlpha;
				origAlpha = nullMagicNumber;
			}
			if (origAmount != nullOrigAmount) {
				// revert amount
				__instance.amount = origAmount;
				origAmount = nullOrigAmount;
			}
		}
	}
}
