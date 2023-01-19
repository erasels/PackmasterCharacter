package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.evenoddpack.WindupAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WindUpBomb extends AbstractEvenOddCard{
    public final static String ID = makeID(WindUpBomb.class.getSimpleName());
    private static final int DAMAGE = 8;
    private static final int MAGIC = 15;
    private static final int UMAGIC = 5;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    
    public WindUpBomb() {
        super(ID, COST, TYPE, RARITY, TARGET);
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        initializeDescription();
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }
    
    @Override
    public void upp() {
        upgradeMagicNumber(UMAGIC);
    }
    
    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        initializeDescription();
    }
    
    @Override
    public void applyPowers() {
        super.applyPowers();
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0)
        {
            this.target = CardTarget.ENEMY;
        }
        else
        {
            this.target = CardTarget.NONE;
        }
    }
    
    @Override
    protected String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1)
        {
            return cardStrings.EXTENDED_DESCRIPTION[1];
        }
        else
        {
            return cardStrings.EXTENDED_DESCRIPTION[4];
        }
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new WindupAction(abstractMonster, new DamageInfo(abstractMonster, this.damage, this.damageTypeForTurn), magicNumber, this));
    }
}