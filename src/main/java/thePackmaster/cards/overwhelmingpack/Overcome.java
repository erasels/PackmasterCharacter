package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.overwhelmingpack.OvercomePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Overcome extends AbstractOverwhelmingCard {
    public final static String ID = makeID("Overcome");

    public Overcome() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OvercomePower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}