package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.evenoddpack.PrimeDirectivePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PrimeDirective extends AbstractPackmasterCard {
    public final static String ID = makeID(PrimeDirective.class.getSimpleName());
    private static final int MAGIC = 4;
    private static final int UMAGIC = 1;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    
    public PrimeDirective() {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }
    
    @Override
    public void upp() {
        upgradeMagicNumber(UMAGIC);
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.applyToSelf(new PrimeDirectivePower(abstractPlayer, magicNumber));
    }
}
