package thePackmaster.cards.quietpack;



import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.quietpack.HammerThrowPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class HammerThrow extends AbstractPackmasterCard {
    public final static String ID = makeID("HammerThrow");

    public HammerThrow() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HammerThrowPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upMagic(2);
    }
}


