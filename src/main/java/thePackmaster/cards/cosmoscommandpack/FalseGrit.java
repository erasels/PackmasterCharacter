package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.cosmoscommandpack.FalseGritAction;
import thePackmaster.cards.marisapack.AmplifyCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class FalseGrit extends AbstractCosmosCard implements AmplifyCard {
    public final static String ID = makeID("FalseGrit");

    public FalseGrit() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = 5;
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FalseGritAction(block, magicNumber, this));
    }

    @Override
    public boolean skipUseOnAmplify() {
        return true;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        atb(new FalseGritAction(block, magicNumber*2, this));
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }

    @Override
    public void upp() {
        upgradeBlock(1);
    }
}