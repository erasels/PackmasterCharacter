package thePackmaster.powers.boardgamepack;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class JailPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(JailPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public JailPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, amount);
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        return 0;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(info.type == DamageInfo.DamageType.NORMAL || info.type == DamageInfo.DamageType.THORNS)
            return 0;
        return damageAmount;
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return card.type != AbstractCard.CardType.ATTACK;
    }

    @Override
    public void atStartOfTurn() {
        if(amount <= 1)
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        else
            addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1]+ amount + DESCRIPTIONS[2];
    }

}
