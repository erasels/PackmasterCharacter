package thePackmaster.cards.startuppack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.startuppack.DebutPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Debut extends AbstractStartUpCard {
    public final static String ID = makeID("Debut");

    public Debut() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.applyToSelf(new DebutPower(abstractPlayer, this.magicNumber));
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}
