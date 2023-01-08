package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.att;

public class ChargeUpPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(ChargeUpPower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static int THRESHOLD = 8, MODIFY_AMT = 2;

    public ChargeUpPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (amount >= THRESHOLD && (card.type == AbstractCard.CardType.ATTACK)) {
            flash();
            att(new ReducePowerAction(owner, owner, this, THRESHOLD));
        }
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        if (amount >= THRESHOLD && type == DamageInfo.DamageType.NORMAL) {
            return damage * MODIFY_AMT;
        }
        return damage;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ChargeUpPower(amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], THRESHOLD, THRESHOLD);
    }

}




