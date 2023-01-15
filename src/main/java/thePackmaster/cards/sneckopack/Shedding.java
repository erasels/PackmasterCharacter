package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.sneckopack.SheddingPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Shedding extends AbstractPackmasterCard {


    public final static String ID = makeID("Shedding");

    public Shedding() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new SheddingPower(p,magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
