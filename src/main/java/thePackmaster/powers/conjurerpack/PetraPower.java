package thePackmaster.powers.conjurerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PetraPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("PetraPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static final int EFFECT = 35;

    public PetraPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public void atEndOfRound() {
        Wiz.reducePower(this);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            int amt = getBlockAmount(damageAmount);
            if(amt > 0)
                addToBot(new ApplyPowerAction(info.owner, info.owner, new NextTurnBlockPower(info.owner, amt), amt));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + EFFECT + DESCRIPTIONS[1];
    }

    public int getBlockAmount(int damage)
    {
        return Math.max(0, (int) (damage * EFFECT / 100f));
    }
}
