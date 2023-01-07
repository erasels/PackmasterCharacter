package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.cosmoscommandpack.FalseGritAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.marisapack.AmplifyCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class FalseGrit extends AbstractPackmasterCard implements AmplifyCard {
    public final static String ID = makeID("FalseGrit");

    public FalseGrit() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FalseGritAction(false));
    }

    @Override
    public boolean skipUseOnAmplify() {
        return true;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        atb(new FalseGritAction(true));
    }

    @Override
    public int getAmplifyCost() {
        return baseMagicNumber;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
    }
}