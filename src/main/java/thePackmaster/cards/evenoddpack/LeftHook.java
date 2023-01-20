package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LeftHook extends AbstractEvenOddCard{
    public final static String ID = makeID(LeftHook.class.getSimpleName());
    private static final int DAMAGE = 3;
    private static final int UDAMAGE = 2;
    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    
    public LeftHook() {
        super(ID, COST, TYPE, RARITY, TARGET);
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
        baseDamage = DAMAGE;
    }
    
    @Override
    public void upp() {
        upgradeDamage(UDAMAGE);
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
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0)
        {
            return   cardStrings.DESCRIPTION
                    + cardStrings.EXTENDED_DESCRIPTION[0]
                    + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        else
        {
            return cardStrings.DESCRIPTION;
        }
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        returnToHand = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1;
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }
}
