package thePackmaster.actions.thieverypack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import thePackmaster.powers.thieverypack.ThieveryMasteryPower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/// This piece of sh*t is designed to only be used with Null Hammer and Magnet.
public class StealPowerAction extends AbstractGameAction {
	private static final float POWER_ICON_PADDING_X = 48.0F * Settings.scale;
	public float t;

	public float targetX, targetY;
	public HashSet<AbstractPower> affectedPowers;

	public enum AnimationMode {TO_PLAYER, HAMMERED}

	public final AnimationMode mode;

	public final ArrayList<AbstractMonster> monsters;
	public final ArrayList<String> powIDs;

	public static StealPowerAction activatedInstance = null;

	public static boolean didMiss;

	/**
	 * StealPowerAction can steal specified powers from enemies.
	 *
	 * @param monsters      List of monsters
	 * @param powIDs        The ID of powers for steal
	 * @param animationMode TO_PLAYER moves the powers to the player, HAMMERED moves the powers downwards and make them disappear
	 * @param amount        How much amount you can steal. Set to 0 or below for no limit
	 */
	public StealPowerAction(ArrayList<AbstractMonster> monsters, ArrayList<String> powIDs, AnimationMode animationMode, int amount) {
		actionType = ActionType.WAIT;
		duration = startDuration = Settings.ACTION_DUR_LONG;
		this.monsters = monsters;
		this.powIDs = powIDs;
		this.mode = animationMode;
		this.amount = amount;
	}

	// for Null Hammer
	public StealPowerAction(AbstractMonster m, ArrayList<String> powIDs, AnimationMode mode) {
		this(new ArrayList<AbstractMonster>() {{
			if (m != null) {
				add(m);
			}
		}}, powIDs, mode, -1);
		duration = startDuration = Settings.ACTION_DUR_MED;
	}

	// for Magnet
	public StealPowerAction(ArrayList<AbstractMonster> monsters, String powID, AnimationMode mode) {
		this(monsters, new ArrayList<String>() {{
			add(powID);
		}}, mode, -1);
	}

	// for Strength Sap
	public StealPowerAction(AbstractMonster m, String powID, AnimationMode mode, int amount) {
		this(new ArrayList<AbstractMonster>() {{
			add(m);
		}}, new ArrayList<String>() {{
			add(powID);
		}}, mode, amount);
		duration = startDuration = Settings.ACTION_DUR_LONG * 0.75f;
	}

	public void calcPosition(float[] x, float[] y, Color c, boolean isAmount) {
		if (mode == AnimationMode.TO_PLAYER) {
			x[0] = MathUtils.lerp(x[0], targetX + (isAmount ? 32.0F * Settings.scale : 0), t);
			y[0] = MathUtils.lerp(y[0], targetY - (isAmount ? 18.0F * Settings.scale : 0), t);
		} else if (mode == AnimationMode.HAMMERED) {
			if (t < 0.4f) {
				float newt = (0.4f - t) / 0.4f;
				newt = 1 - newt * newt * newt;
				x[0] += targetX * newt;
				y[0] += targetY * newt;
			} else if (t < 0.7f) {
				x[0] += targetX;
				y[0] += targetY;
			} else {
				float newt = (t - 0.7f) / 0.3f;
				c.a *= (1 - newt) * (1 - newt);
				x[0] += targetX;
				y[0] += targetY - 20.0f * newt * Settings.scale;
			}
		}
	}

	@Override
	public void update() {
		AbstractPlayer p = AbstractDungeon.player;

		if (duration == startDuration) {
			if (monsters.isEmpty()) {
				didMiss = true;
				isDone = true;
				activatedInstance = null;
				return;
			}

			if (mode == AnimationMode.TO_PLAYER) {
				int index = 0;
				for (; index < p.powers.size(); index++) {
					AbstractPower pow = p.powers.get(index);
					if (powIDs.contains(pow.ID)) {
						break;
					}
				}
				targetX = p.hb.cX - p.hb.width / 2.0F + 10.0F * Settings.scale + index * POWER_ICON_PADDING_X;
				targetY = p.hb.cY - p.hb.height / 2.0F + ReflectionHacks.<Float>getPrivate(p, AbstractCreature.class, "hbYOffset") - 48.0F * Settings.scale;
			} else if (mode == AnimationMode.HAMMERED) {
				targetX = MathUtils.random(-5.0f, 5.0f) * Settings.scale;
				targetY = -50.0f * Settings.scale;
			}

			affectedPowers = new HashSet<>();
			for (AbstractMonster m : monsters) {
				for (AbstractPower pow : m.powers) {
					if (powIDs.contains(pow.ID) && pow.amount > 0) {
						affectedPowers.add(pow);
					}
				}
			}

			if (affectedPowers.isEmpty()) {
				didMiss = true;
				isDone = true;
				activatedInstance = null;
				return;
			}
			t = 0;
		}
		tickDuration();
		didMiss = false;

		if (isDone) {
			t = 1;
			HashMap<String, Integer> stackAmountMap = new HashMap<>();
			for (AbstractPower pow : affectedPowers) {
				int stealAmount = pow.amount;
				if (amount > 0) {
					stealAmount = Math.min(stealAmount, amount);
				}
				if (stackAmountMap.containsKey(pow.ID)) {
					stackAmountMap.put(pow.ID, stackAmountMap.get(pow.ID) + stealAmount);
				} else {
					stackAmountMap.put(pow.ID, stealAmount);
				}
			}

			int multiplier = 1;
			AbstractPower tmp = AbstractDungeon.player.getPower(ThieveryMasteryPower.POWER_ID);
			if (tmp != null) {
				tmp.flash();
				multiplier += tmp.amount;
			}

			addToTop(new AbstractGameAction() {
				@Override
				public void update() {
					activatedInstance = null;
					isDone = true;
				}
			});

			for (int i = powIDs.size() - 1; i >= 0; i--) {
				String id = powIDs.get(i);
				if (stackAmountMap.containsKey(id)) {
					int gainAmount = stackAmountMap.get(id) * multiplier;
					if (gainAmount > 0) {
						AbstractPower pow = getPowerFromID(p, id, gainAmount);
						addToTop(new ApplyPowerAction(p, p, pow, gainAmount, true) {
							@Override
							public void update() {
								super.update();
								isDone = true;
							}
						});
					}
				}
			}
			for (AbstractPower pow : affectedPowers) {
				if (amount > 0) {
					AbstractPower newPower = getPowerFromID(pow.owner, pow.ID, -(Math.min(amount, pow.amount)));
					newPower.type = NeutralPowertypePatch.NEUTRAL; // Artifact cannot prevent stealing
					addToTop(new ApplyPowerAction(pow.owner, p, newPower) {
						public void update() {
							super.update();
							isDone = true;
							affectedPowers.remove(pow);
						}
					});
				} else {
					addToTop(new RemoveSpecificPowerAction(pow.owner, p, pow) {
						@Override
						public void update() {
							super.update();
							isDone = true;
						}
					});
				}
			}
		} else {
			t = (startDuration - duration) / startDuration;
			activatedInstance = this;
		}
	}

	private AbstractPower getPowerFromID(AbstractCreature c, String id, int gainAmount) {
		switch (id) {
			case StrengthPower.POWER_ID:
				return new StrengthPower(c, gainAmount);
			case ArtifactPower.POWER_ID:
				return new ArtifactPower(c, gainAmount);
			case PlatedArmorPower.POWER_ID:
				return new PlatedArmorPower(c, gainAmount);
			case ThornsPower.POWER_ID:
				return new ThornsPower(c, gainAmount);
			case MetallicizePower.POWER_ID:
				return new MetallicizePower(c, gainAmount);
		}
		// should not happen
		return new StrengthPower(c, gainAmount);
	}
}
