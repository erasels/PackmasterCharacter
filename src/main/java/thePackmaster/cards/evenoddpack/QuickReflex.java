package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.evenoddpack.QuickReflexAction;
import thePackmaster.actions.evenoddpack.SwordAndBoardAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class QuickReflex extends AbstractEvenOddCard{
    public final static String ID = makeID(QuickReflex.class.getSimpleName());
    private static final int BLOCK = 6;
    private static final int UBLOCK = 2;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    
    public QuickReflex() {
        super(ID, COST, TYPE, RARITY, TARGET);
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
        baseBlock = BLOCK;
    }
    
    @Override
    public void upp() {
        upgradeBlock(UBLOCK);
    }
    
    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
    }
    
    @Override
    protected String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0)
        {
            return cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        else
        {
            return cardStrings.DESCRIPTION;
        }
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new QuickReflexAction(block));
    }
}
