package thePackmaster.cards.lockonpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.cardmodifiers.creativitypack.AccumulativeDamageModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Reiji extends AbstractLockonCard {

    public final static String ID = makeID(Reiji.class.getSimpleName());

    public Reiji() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 0;
        secondMagic = baseSecondMagic = 3;
        isInnate = true;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new LockOnPower(m, secondMagic)));
        if (magicNumber > 0 && m.hasPower(LockOnPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new FocusPower(p, magicNumber)));
        }
    }

}
