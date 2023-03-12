package thePackmaster.cards.scrypluspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;
import thePackmaster.powers.scrypluspack.AnticipatePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Anticipate extends AbstractScryPlusCard{

    public final static String ID = makeID(Anticipate.class.getSimpleName());
    public Anticipate() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new AnticipatePower(p, magicNumber));
    }
}
