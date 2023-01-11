package thePackmaster.powers.monsterhunterpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DelayedDamagePower extends AbstractPackmasterPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "DelayedDamagePower";
    public static final String POWER_ID = makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DelayedDamagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, LOC_NAME, PowerType.BUFF, true, owner, 2);
        name = LOC_NAME;
        isTwoAmount = true;
        amount2 = amount;
        canGoNegative2 = false;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount2 += stackAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
            if (amount <= 1){
                AbstractDungeon.actionManager.addToBottom(new AnimateJumpAction(AbstractDungeon.player));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(this.owner.hb.cX, this.owner.hb.cY, new Color(0.1F, 1.0F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(AbstractDungeon.player, this.amount2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON));
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            else {
                amount--;
                this.flashWithoutSound();
                updateDescription();
            }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}