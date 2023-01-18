package thePackmaster.patches.thieverypack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.*;
import javassist.CtBehavior;
import thePackmaster.powers.thieverypack.MindControlledPower;

import java.util.ArrayList;
import java.util.stream.Collectors;

// Copied and modified from DTMod
public class MindControlPatch {
	public static AbstractCreature redirectTarget = null;

	@SpirePatch2(clz = GameActionManager.class, method = "getNextAction")
	public static class ChangeTargetPatch {
		@SpireInsertPatch(locator = BeforeTakeTurnLocator.class)
		public static void Insert(AbstractMonster ___m) {
			AbstractPower p = ___m.getPower(MindControlledPower.POWER_ID);
			if (p != null) {
				p.flashWithoutSound();
				ArrayList<AbstractMonster> eligibleEnemies = AbstractDungeon.getMonsters().monsters.stream().filter(
					m -> !m.hasPower(MindControlledPower.POWER_ID) && !m.halfDead && !m.isDying && !m.isEscaping
				).collect(Collectors.toCollection(ArrayList::new));
				if (eligibleEnemies.isEmpty()) {
					redirectTarget = ___m;
					return;
				}

				redirectTarget = eligibleEnemies.get(MindControlledPower.targetRng.random(eligibleEnemies.size() - 1));
			}
		}

		@SpirePostfixPatch
		public static void Postfix(GameActionManager __instance) {
			redirectTarget = null;
		}

		private static class BeforeTakeTurnLocator extends SpireInsertLocator {
			@Override
			public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
				Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "takeTurn");
				return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
			}
		}
	}

	@SpirePatch2(clz = GameActionManager.class, method = "addToBottom")
	@SpirePatch2(clz = GameActionManager.class, method = "addToTop")
	public static class ActionRedirectPatch {
		@SpirePrefixPatch
		public static void Prefix(@ByRef AbstractGameAction[] action) {
			if (redirectTarget != null) {
				if (action[0].target == AbstractDungeon.player) {
					action[0].target = redirectTarget;

					if (action[0] instanceof ApplyPowerAction) {
						AbstractPower p = ReflectionHacks.getPrivate(action[0], ApplyPowerAction.class, "powerToApply");
						if (p instanceof WeakPower) {
							action[0] = new ApplyPowerAction(redirectTarget, action[0].source, new WeakPower(redirectTarget, p.amount, true));
						} else if (p instanceof PoisonPower) {
							action[0] = new ApplyPowerAction(redirectTarget, action[0].source, new PoisonPower(redirectTarget, action[0].source, p.amount));
						} else if (p instanceof StrengthPower) {
							action[0] = new ApplyPowerAction(redirectTarget, action[0].source, new StrengthPower(redirectTarget, p.amount));
						} else {
							// All other debuffs will be changed to Vulnerable
							int amount = p.amount == 0 ? 1 : Math.abs(p.amount);
							action[0] = new ApplyPowerAction(redirectTarget, action[0].source, new VulnerablePower(redirectTarget, amount, true), amount);
						}
					}
				}
			}
		}
	}

	@SpirePatch2(clz = AbstractCreature.class, method = "renderRedHealthBar")
	public static class HPBarRenderPatch {
		static Color mindControlledColor = new Color(0.3F, 0.7F, 0.7F, 0.0F);

		@SpireInsertPatch(locator = AfterInitialSetColor.class)
		public static void Insert(AbstractCreature __instance, SpriteBatch sb) {
			if (__instance.hasPower(MindControlledPower.POWER_ID)) {
				mindControlledColor.a = __instance.hbAlpha;
				sb.setColor(mindControlledColor);
			}
		}

		private static class AfterInitialSetColor extends SpireInsertLocator {
			@Override
			public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
				Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "hasPower");
				return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
			}
		}
	}

	@SpirePatch2(clz = AbstractMonster.class, method = "damage")
	@SpirePatch2(clz = MonsterGroup.class, method = "applyPreTurnLogic")
	@SpirePatch2(clz = MonsterGroup.class, method = "applyEndOfTurnPowers")
	public static class SuicidePatch {
		@SpirePostfixPatch
		public static void Postfix() {
			MindControlledPower.checkForCombatEnd();
		}
	}
}
