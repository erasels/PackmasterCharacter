package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.boardgamepack.RollAction;
import thePackmaster.powers.boardgamepack.AdvantagePower;
import thePackmaster.powers.boardgamepack.OneTimeAdvantagePower;
import thePackmaster.powers.summonspack.JinxPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.*;
import static thePackmaster.util.Wiz.*;

public class Leprechaun extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Leprechaun.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/orbs/summonsPack/Leprechaun.png");
    private static final float SPIRIT_WIDTH = 96.0f;

    private final static int BASE_PASSIVE = 3;
    private final static int BASE_EVOKE = 3;

    private final BobEffect lepBobEffect = new BobEffect(2f, 2f);
    private String passiveString;
    private String oString;
    private ArrayList<Integer> dice;
    private int modifier;

    public Leprechaun()
    {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH);
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
    }

    @Override
    public void applyFocus() {
        AbstractPower pow = adp().getPower(FocusPower.POWER_ID);
        if (pow == null) {
            passiveAmount = basePassiveAmount;
            evokeAmount = baseEvokeAmount;
        } else {
            passiveAmount = basePassiveAmount + pow.amount;
            evokeAmount = baseEvokeAmount + pow.amount;
        }
        if (passiveAmount < 0)
            passiveAmount = 0;
        if (evokeAmount < 0)
            evokeAmount = 0;
        setDice();
        if (dice.size() > 0) {
            oString = passiveString.replace("#b", "");
            oString = oString.replace(" ", "");
        }
        else
            oString = "";
    }

    public void setDice() {
        if (dice == null)
            dice = new ArrayList<>();
        dice.clear();
        modifier = 0;
        if (passiveAmount == 0) {
            passiveString = "nothing";
            return;
        }

        int count = passiveAmount / 10;
        int remainderAmount = passiveAmount % 10;

        for (int i = 0; i < count; i++)
            dice.add(20);
        if (count > 0) {
            passiveString = count + "d20";
            if (remainderAmount == 0)
                return;
            passiveString += " #b+ #b";
        } else
            passiveString = "";

        if (remainderAmount < 7) {
            dice.add(remainderAmount*2);
            passiveString += "1d" + remainderAmount*2;
        } else if (remainderAmount == 7) {
            dice.add(6);
            dice.add(6);
            modifier = 1;
            passiveString += "2d6 #b+ #b1";
        } else if (remainderAmount == 8) {
            dice.add(8);
            dice.add(8);
            modifier = -1;
            passiveString += "2d8 #b- #b1";
        } else {
            dice.add(8);
            dice.add(8);
            modifier = 1;
            passiveString += "2d8 #b+ #b1";
        }
    }

    public int getDiceCount() {
        return dice.size();
    }

    @Override
    public void onStartOfTurn() {
        ArrayList<Integer> sides = new ArrayList<>(dice);

        if (sides.size() > 0)
            atb(new RollAction(sides, modifier, false));
    }

    @Override
    public void onEvoke() {
        if(evokeAmount > 0)
            for (AbstractMonster m : Wiz.getEnemies())
                applyToEnemyTop(m, new JinxPower(m, evokeAmount));
    }

    @Override
    public void updateAnimation() {
        lepBobEffect.update();
        cX = MathHelper.orbLerpSnap(cX, AbstractDungeon.player.animX + tX);
        cY = MathHelper.orbLerpSnap(cY, AbstractDungeon.player.animY + tY);
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - SPIRIT_WIDTH /2F, cY - SPIRIT_WIDTH /2F + lepBobEffect.y, SPIRIT_WIDTH /2F, SPIRIT_WIDTH /2F,
                SPIRIT_WIDTH, SPIRIT_WIDTH, scale, scale, 0f, 0, 0, (int) SPIRIT_WIDTH, (int) SPIRIT_WIDTH,
                false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        if (oString != null && dice.size() > 0)
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, oString, cX + NUM_X_OFFSET,
                cY + NUM_Y_OFFSET, c, fontScale);
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveString + DESCRIPTIONS[1] + evokeAmount + DESCRIPTIONS[2];
    }

    public static void staticStartOfTurn() {
        if (adp() == null)
            return;
        int dice = 0;
        int leps = 0;
        boolean diceCalc = false;
        for (AbstractOrb orb : adp().orbs)
            if (orb instanceof Leprechaun) {
                ((Leprechaun) orb).setDice();
                if (!diceCalc) {
                    diceCalc = true;
                    dice = ((Leprechaun) orb).getDiceCount();
                }
                leps++;
            }

        int diceCount = dice * leps;
        if (diceCount == 0)
            return;

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (diceCount == 1)
                    CardCrawlGame.sound.play(DIE_KEY, 0.1f);
                else if (diceCount >= 2 && diceCount <= 4)
                    CardCrawlGame.sound.play(DICE_KEY, 0.1f);
                else if (diceCount >= 5) {
                    int times = diceCount / 5;
                    if (times > 5)
                        times = 5;
                    for (int i = 0; i < times; i++) {
                        att(new AbstractGameAction() {
                            @Override
                            public void update() {
                                CardCrawlGame.sound.play(DICELOTS_KEY, 0.1f);
                                isDone = true;
                            }
                        });
                        if (i != 0) {
                            att(new WaitAction(0.1f));
                        }
                    }
                }
            }
        });
    }

    public static int roll(int sides) {
        int curRoll = 0;
        int advantage = getAdvantage();
        for(int roll = 0; roll <= advantage; roll++)
        {
            int newRoll = AbstractDungeon.cardRandomRng.random(sides - 1) + 1;

            if(newRoll > curRoll)
                curRoll = newRoll;
        }
        return curRoll;
    }

    private static int getAdvantage() {
        int adv = 0;
        if(adp().hasPower(OneTimeAdvantagePower.POWER_ID))
        {
            adv += adp().getPower(OneTimeAdvantagePower.POWER_ID).amount;
            att(new RemoveSpecificPowerAction(adp(), adp(), adp().getPower(OneTimeAdvantagePower.POWER_ID)));
        }
        if(adp().hasPower(AdvantagePower.POWER_ID))
            adv += adp().getPower(AdvantagePower.POWER_ID).amount;
        return adv;
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Leprechaun();
    }
}