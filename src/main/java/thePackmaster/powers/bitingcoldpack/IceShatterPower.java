package thePackmaster.powers.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class IceShatterPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("IceShatterPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public boolean notActivatedForThisHPLoss;

    public IceShatterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.DEBUFF, false, owner, amount);
        notActivatedForThisHPLoss = true;
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && AbstractDungeon.actionManager.turnHasEnded && notActivatedForThisHPLoss) {
            notActivatedForThisHPLoss = false;
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    notActivatedForThisHPLoss = true;
                    this.isDone = true;
                }
            });
            att(new DamageAction(this.owner, new DamageInfo(Wiz.p(), this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.flash();
        }
    }

    @Override
    public void atEndOfRound() {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
