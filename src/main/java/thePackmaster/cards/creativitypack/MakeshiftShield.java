package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.creativitypack.MakeshiftShieldPower;
import thePackmaster.powers.creativitypack.MakeshiftSwordPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MakeshiftShield extends AbstractCreativityCard {

    public final static String ID = makeID(MakeshiftShield.class.getSimpleName());

    public MakeshiftShield() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void upp() {
        upMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p, p, new MakeshiftShieldPower(p, magicNumber)));
    }
}
