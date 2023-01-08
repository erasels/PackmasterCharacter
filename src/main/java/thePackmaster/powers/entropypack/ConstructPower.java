package thePackmaster.powers.entropypack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConstructPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ConstructPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ConstructPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);

        this.priority = -42069;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL) {
            return Math.max(0, damage - this.amount);
        }
        return damage;
    }

    @Override
    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    public void updateDescription() {
        if (this.owner.isPlayer) {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
        else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}