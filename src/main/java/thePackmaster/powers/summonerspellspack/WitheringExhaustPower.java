package thePackmaster.powers.summonerspellspack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.actions.summonerspellspack.HalveEnemyAttacksAction;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WitheringExhaustPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("WitheringExhaustPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean alreadyDone;
    private EnemyMoveInfo expectedMoveInfo = null;

    public WitheringExhaustPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, 1);

        alreadyDone = false;
    }

    @Override
    public void onInitialApplication() {
        trigger(true);
    }

    @Override
    public void stackPower(int stackAmount) {
        trigger(false);
    }

    private void trigger(boolean isInitialApplication) {
        if (owner instanceof AbstractMonster)
        {
            AbstractMonster targetMonster = (AbstractMonster)owner;
            if (targetMonster.intent == AbstractMonster.Intent.ATTACK ||
                    targetMonster.intent == AbstractMonster.Intent.ATTACK_BUFF ||
                    targetMonster.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                    targetMonster.intent == AbstractMonster.Intent.ATTACK_DEFEND)
            {
                EnemyMoveInfo targetMove = (EnemyMoveInfo) ReflectionHacks.getPrivate(targetMonster, AbstractMonster.class, "move");

                if (!isInitialApplication)
                    this.amount += 1;

                if (targetMove.isMultiDamage && targetMove.multiplier > 1)
                {
                    double attacks = Math.ceil((float)targetMove.multiplier / 2f);
                    targetMove.multiplier = (int)attacks;

                    if (targetMove.multiplier == 1)
                        targetMove.isMultiDamage = false;

                    expectedMoveInfo = new EnemyMoveInfo(targetMove.nextMove, targetMove.intent, targetMove.baseDamage, targetMove.multiplier, targetMove.isMultiDamage);
                    ReflectionHacks.setPrivate(targetMonster, AbstractMonster.class, "move", expectedMoveInfo);
                    targetMonster.createIntent();

                    for (AbstractPower p : targetMonster.powers)
                        p.updateDescription();
                }
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        if (!(owner instanceof AbstractMonster)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this.ID));
            return;
        }

        EnemyMoveInfo targetMove = (EnemyMoveInfo) ReflectionHacks.getPrivate((AbstractMonster)owner, AbstractMonster.class, "move");
        if (!targetMove.equals(expectedMoveInfo))
        {
            this.amount = 0;
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this.ID));
        }



    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (this.amount > 0 && !alreadyDone && info.type == DamageInfo.DamageType.NORMAL) {
            AbstractDungeon.actionManager.addToTop(new HalveEnemyAttacksAction(owner, this.amount));
            alreadyDone = true;
        }

        super.onAttack(info, damageAmount, target);



/*
        if (this.amount > 0 && !alreadyDone.contains(info) && info.type == DamageInfo.DamageType.NORMAL) {
            DamageInfo copyDamageInfo = new DamageInfo(info.owner, info.output, info.type); //copy the final damage, since applyPowers won't get called
            AbstractGameAction.AttackEffect attackEffect = AbstractGameAction.AttackEffect.NONE;

            if (AbstractDungeon.actionManager.currentAction != null && AbstractDungeon.actionManager.currentAction.actionType == AbstractGameAction.ActionType.DAMAGE)
            {
                if (AbstractDungeon.actionManager.currentAction.attackEffect != null)
                    attackEffect = AbstractDungeon.actionManager.currentAction.attackEffect;
            }

            alreadyDone.add(copyDamageInfo);

            int extraHits = (int)Math.ceil(Math.pow(0.5f, this.amount)) - 1;

            AbstractDungeon.actionManager.
            for (int i = 0; i < extraHits; i++)
            {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(target,
                        copyDamageInfo, attackEffect));
            }
        }

 */
    }

    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    @Override
    public void updateDescription() {
        description = this.amount == 1 ?
                DESCRIPTIONS[0] :
                DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }


}
