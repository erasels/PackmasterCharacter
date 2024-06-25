package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.marisapack.OnChargeUpCard;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.stream.Stream;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ChargeUpPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(ChargeUpPower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static int THRESHOLD = 6, MODIFY_AMT = 2;

    public static int chargeUpGainThisCombat = 0;

    public ChargeUpPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public void onInitialApplication() {
        chargeUpGainThisCombat += amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        chargeUpGainThisCombat += stackAmount;
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (amount >= THRESHOLD && (card.type == AbstractCard.CardType.ATTACK)) {
            flash();
            atb(new ReducePowerAction(owner, owner, this, THRESHOLD));
            Stream.concat(Wiz.drawPile().group.stream(), Wiz.discardPile().group.stream())
                    .filter(c -> c instanceof OnChargeUpCard)
                    .forEachOrdered(c -> ((OnChargeUpCard) c).onChargeUpConsumed(card));
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

    public static void receiveBattleStart() {
        chargeUpGainThisCombat = 0;
    }
}




