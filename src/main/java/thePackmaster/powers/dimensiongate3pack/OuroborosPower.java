package thePackmaster.powers.dimensiongate3pack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OuroborosPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("OuroborosPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean triggeredThisTurn = false;

    public OuroborosPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);

    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount <= Wiz.p().currentBlock && !triggeredThisTurn) {
            Wiz.applyToSelf(new StrengthPower(Wiz.p(), amount));
            triggeredThisTurn = true;
            this.flash();
            updateDescription();
        }
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        triggeredThisTurn = false;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        triggeredThisTurn = false;
        updateDescription();
    }

    @Override
    public void updateDescription() {

        if (triggeredThisTurn) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }
}
