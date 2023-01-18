package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.creativitypack.MakeshiftSwordPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MakeshiftSword extends AbstractCreativityCard {

    public final static String ID = makeID(MakeshiftSword.class.getSimpleName());

    public MakeshiftSword() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void upp() {
        upMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p, p, new MakeshiftSwordPower(p, magicNumber)));
    }
}
