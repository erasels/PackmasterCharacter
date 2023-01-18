package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.evenoddpack.ElementOfSurpriseAction;
import thePackmaster.actions.evenoddpack.QuickReflexAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ElementOfSurprise extends AbstractEvenOddCard{
    public final static String ID = makeID(ElementOfSurprise.class.getSimpleName());
    private static final int MAGIC = 1;
    private static final int COST = 1;
    private static final int UCOST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    
    public ElementOfSurprise() {
        super(ID, COST, TYPE, RARITY, TARGET);
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
        magicNumber = baseMagicNumber = MAGIC;
    }
    
    @Override
    public void upp() {
        upgradeBaseCost(UCOST);
    }
    
    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }
    
    @Override
    protected String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0)
        {
            return cardStrings.EXTENDED_DESCRIPTION[1];
        }
        else
        {
            return cardStrings.DESCRIPTION;
        }
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ElementOfSurpriseAction(magicNumber));
    }
}
