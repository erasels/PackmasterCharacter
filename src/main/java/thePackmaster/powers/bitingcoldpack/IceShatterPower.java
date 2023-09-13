package thePackmaster.powers.bitingcoldpack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

public class IceShatterPower extends AbstractPackmasterPower implements OnLoseBlockPower {
    public static final String POWER_ID = makeID("IceShatterPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public boolean activated;

    public IceShatterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void onInitialApplication() {
        activated = false;
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        // If the damage is positive, it's the enemy turn, AND this power is not considered "activated"
        if (damageAmount > 0 && AbstractDungeon.actionManager.turnHasEnded && !activated) {
            activated = true;
            Wiz.doDmg(owner, amount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, true);
            flash();
        // Otherwise, set the power to not be "activated"
        } else if (activated) {
            activated = false;
        }
        // Basically this means that the damage from this power itself doesn't retrigger the power infinitely
    }

    // In case the creature has Barricade (damn you Spheric Guardian)
    @Override
    public int onLoseBlock(DamageInfo damageInfo, int damageAmount) {
        if (owner.currentBlock >= damageAmount) activated = false;
        return damageAmount;
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
