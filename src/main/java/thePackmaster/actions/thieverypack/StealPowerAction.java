package thePackmaster.actions.thieverypack;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.powers.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.thieverypack.ThieveryMasteryPower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/// This piece of sh*t is only tested with the Thievery pack.
public class StealPowerAction extends AbstractGameAction {
	public static final String ID = SpireAnniversary5Mod.makeID("StealPowerAction");
	private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);

	private static final float POWER_ICON_PADDING_X = 48.0F * Settings.scale;
	public float t;

	public HashSet<AbstractPower> affectedPowers;

	public enum AnimationMode {TO_PLAYER, HAMMERED}

	public final AnimationMode animationMode;

	public final ArrayList<AbstractMonster> monsters;
	public final HashMap<String, Integer> powIDAmountMap;
	public HashMap<String, Float> targetXMap, targetYMap;

	public static StealPowerAction activatedInstance = null;

	public static boolean didMiss;
	public static boolean canGoNegative;

	/**
	 * StealPowerAction can steal specified powers from enemies.
	 *
	 * @param monsters       List of monsters
	 * @param powIDAmountMap The map of power ID and their amount. Amount of 0 or below means no limit
	 * @param animationMode  TO_PLAYER moves the powers to the player, HAMMERED moves the powers downwards and make them disappear
	 */
	public StealPowerAction(ArrayList<AbstractMonster> monsters, HashMap<String, Integer> powIDAmountMap, AnimationMode animationMode) {
		actionType = ActionType.WAIT;
		duration = startDuration = Settings.ACTION_DUR_LONG * 0.75f;
		this.monsters = monsters;
		this.powIDAmountMap = powIDAmountMap;
		this.animationMode = animationMode;
		canGoNegative = false;
	}

	// for Null Hammer
	public StealPowerAction(AbstractMonster m, HashMap<String, Integer> powIDAmountMap, AnimationMode animationMode) {
		this(new ArrayList<AbstractMonster>() {{
			if (m != null) {
				add(m);
			}
		}}, powIDAmountMap, animationMode);
		canGoNegative = true;
	}

	// for Magnet (unupgraded)
	public StealPowerAction(AbstractMonster m, ArrayList<String> powIDs, AnimationMode animationMode) {
		this(new ArrayList<AbstractMonster>() {{
			if (m != null) {
				add(m);
			}
		}}, powIDs, animationMode);
	}

	// for Magnet (upgraded)
	public StealPowerAction(ArrayList<AbstractMonster> monsters, ArrayList<String> powIDs, AnimationMode animationMode) {
		this(monsters, new HashMap<String, Integer>() {{
			for (String id : powIDs) {
				put(id, -1);
			}
		}}, animationMode);
	}

	// for Strength Sap
	public StealPowerAction(AbstractMonster m, String powID, AnimationMode animationMode, int amount) {
		this(new ArrayList<AbstractMonster>() {{
			add(m);
		}}, new HashMap<String, Integer>() {{
			put(powID, amount);
		}}, animationMode);
	}

	public void calcPosition(String powID, float[] x, float[] y, Color c, boolean isAmount) {
		if (animationMode == AnimationMode.TO_PLAYER) {
			float targetX = targetXMap.get(powID);
			float targetY = targetYMap.get(powID);
			x[0] = MathUtils.lerp(x[0], targetX + (isAmount ? 32.0F * Settings.scale : 0), t);
			y[0] = MathUtils.lerp(y[0], targetY - (isAmount ? 18.0F * Settings.scale : 0), t);
		} else if (animationMode == AnimationMode.HAMMERED) {
			float targetX = 0;
			float targetY = -50.0f * Settings.scale;
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

			if (animationMode == AnimationMode.TO_PLAYER) {
				targetXMap = new HashMap<>();
				targetYMap = new HashMap<>();
				for (String ID : powIDAmountMap.keySet()) {
					int index = 0;
					for (; index < p.powers.size(); index++) {
						AbstractPower pow = p.powers.get(index);
						if (pow.ID.equals(ID))
							break;
					}
					targetXMap.put(ID, p.hb.cX - p.hb.width / 2.0F + 10.0F * Settings.scale + index * POWER_ICON_PADDING_X);
					targetYMap.put(ID, p.hb.cY - p.hb.height / 2.0F + ReflectionHacks.<Float>getPrivate(p, AbstractCreature.class, "hbYOffset") - 48.0F * Settings.scale);
				}
			} else if (animationMode == AnimationMode.HAMMERED) {
				// pass
			}

			affectedPowers = new HashSet<>();
			for (AbstractMonster m : monsters) {
				for (AbstractPower pow : m.powers) {
					if (powIDAmountMap.containsKey(pow.ID) && (canGoNegative || pow.amount > 0)) {
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
			int multiplier = 1;
			AbstractPower tmp = AbstractDungeon.player.getPower(ThieveryMasteryPower.POWER_ID);
			if (tmp != null) {
				tmp.flash();
				multiplier = 2;
			}

			addToTop(new AbstractGameAction() {
				@Override
				public void update() {
					activatedInstance = null;
					isDone = true;
				}
			});

			ArrayList<String> incompatibles = new ArrayList<>();
			for (AbstractPower pow : affectedPowers) {
				int mapAmount = powIDAmountMap.get(pow.ID);

				// You gain the power
				int stealAmount;
				if (mapAmount <= 0) {
					stealAmount = pow.amount;
				} else if (canGoNegative) {
					stealAmount = mapAmount;
				} else {
					stealAmount = Math.min(pow.amount, mapAmount);
				}

				int gainAmount = stealAmount * multiplier;
				AbstractPower playerPower = getDuplicatedPower(p, pow, gainAmount);
				if (playerPower == null) {
					incompatibles.add(pow.name);
				} else {
					addToTop(new ApplyPowerAction(p, p, playerPower, gainAmount, true) {
						@Override
						public void update() {
							super.update();
							isDone = true;
						}
					});
				}

				// Enemy loses power
				if (stealAmount == pow.amount || stealAmount > pow.amount && !canGoNegative) {
					addToTop(new RemoveSpecificPowerAction(pow.owner, p, pow) {
						@Override
						public void update() {
							super.update();
							isDone = true;
						}
					});
				} else {
					int finalStealAmount = stealAmount;
					addToTop(new AbstractGameAction() {
						@Override
						public void update() {
							pow.amount -= finalStealAmount;
							affectedPowers.remove(pow);
							isDone = true;
						}
					});
				}
			}

			if (!incompatibles.isEmpty()) {
				String s = String.format(UI_STRINGS.TEXT[incompatibles.size() > 1 ? 1 : 0], (String.join(", ", incompatibles)));
				addToTop(new TalkAction(true, s, 0.1F, 3.5F));
			}
		} else {
			t = (startDuration - duration) / startDuration;
			activatedInstance = this;
		}
	}

	private AbstractPower getDuplicatedPower(AbstractCreature c, AbstractPower pow, int gainAmount) {
		switch (pow.ID) {
			case StrengthPower.POWER_ID:
				return new StrengthPower(c, gainAmount);
			case ArtifactPower.POWER_ID:
				return new ArtifactPower(c, gainAmount);
			case PlatedArmorPower.POWER_ID:
				return new PlatedArmorPower(c, gainAmount);
			case ThornsPower.POWER_ID:
			case SharpHidePower.POWER_ID:
				return new ThornsPower(c, gainAmount);
			case MetallicizePower.POWER_ID:
				return new MetallicizePower(c, gainAmount);
			case IntangiblePower.POWER_ID:
				return new IntangiblePlayerPower(c, gainAmount + 1); // It works!
			case RitualPower.POWER_ID:
				return new RitualPower(c, gainAmount, true);
			case AngerPower.POWER_ID:
				return new AngerPower(c, gainAmount);
			case StasisPower.POWER_ID:
				AbstractCard card = ReflectionHacks.getPrivate(pow, StasisPower.class, "card");
				if (card != null) {
					return new StasisPower(c, card) {
						@Override
						public void onInitialApplication() {
							if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
								addToBot(new MakeTempCardInHandAction(card, false, true));
							} else {
								addToBot(new MakeTempCardInDiscardAction(card, true));
							}
							addToBot(new RemoveSpecificPowerAction(c, c, this));
						}
					};
				} else {
					return null;
				}
		}
		// all other buffs are incompatible
		return null;
	}
}
