package thePackmaster.powers.dragonwrathpack;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.actions.dragonwrathpack.SmiteAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PenancePower extends TwoAmountPower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;
    public static final String POWER_ID = makeID("Penance");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final int EXPLODE_THRESHOLD = 8;
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int Power = 20;

    public PenancePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        // We load those textures here.
        this.loadRegion("heartDef");

        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        amount2 = Power;
        updateDescription();
        if (this.amount >= EXPLODE_THRESHOLD) {
            explode();
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2 = Power;
        if (this.amount >= EXPLODE_THRESHOLD) {
            explode();
        }
    }

    @Override
    public void updateDescription() {
        amount2 = Power;
        description = DESCRIPTIONS[0] + 8 + DESCRIPTIONS[1] + Power + DESCRIPTIONS[2] + DESCRIPTIONS[3];
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.GOLD.cpy());
    }

    @Override
    public AbstractPower makeCopy() {
        return new PenancePower(owner, source, amount);
    }

    @Override
    public int getHealthBarAmount() {
        updateDescription();
        return Power;
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
    }

    private void explode() {
        while (amount >= EXPLODE_THRESHOLD) {
            reducePower(EXPLODE_THRESHOLD);
            CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
            addToBot(new SmiteAction(owner, new DamageInfo(Wiz.p(), Power, DamageInfo.DamageType.HP_LOSS)).fromPenance());
            Power += 10;
        }
        if (amount < 1) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
        updateDescription();
    }

    @Override
    public Color getColor() {
        return Color.GOLD;
    }
}
