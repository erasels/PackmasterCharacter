package thePackmaster.powers.energyandechopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PanacherPower extends AbstractPackmasterPower {
    public AbstractCreature source;

    public static final String POWER_ID = makeID(PanacherPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public PanacherPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        isTwoAmount = true;
        this.amount2 = 5;
        this.updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        this.flash();
        amount2--;
        if (this.amount2 <= 0) {
            this.flash();
            this.amount2 = 5;
            this.addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount2 == 1) {
            this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
        }    }
}

