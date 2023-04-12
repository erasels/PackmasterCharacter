package thePackmaster.powers.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReplenishmentPower extends AbstractPackmasterPower implements OnLoseTempHpPower {

    //DEPRECATED - NOT PLANNED FOR USE

    public static final String POWER_ID = makeID("ReplenishmentPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean triggeredThisTurn = false;


    public ReplenishmentPower(AbstractCreature owner, int amount){
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (triggeredThisTurn) {
            Wiz.atb(new AddTemporaryHPAction(owner, owner, amount));
            triggeredThisTurn = false;
            updateDescription();
        }
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (!triggeredThisTurn && info.owner == owner) {
            this.flash();
            triggeredThisTurn = true;
            updateDescription();
        }
    }

    public int onLoseTempHp(DamageInfo info, int amount){
        if (!triggeredThisTurn && info.owner == owner) {
            this.flash();
            triggeredThisTurn = true;
            updateDescription();
        }
        return info.output;
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
