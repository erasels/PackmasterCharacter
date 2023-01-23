package thePackmaster.cards.evenoddpack;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class GammaWardDummy extends AbstractPackmasterCard {
    public final static String ID = makeID(GammaWardDummy.class.getSimpleName());
    private static final int BLOCK = 12;
    private static final int UBLOCK = 4;
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public GammaWardDummy() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
    }
    
    @Override
    public void upp() {
        upgradeBlock(UBLOCK);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
