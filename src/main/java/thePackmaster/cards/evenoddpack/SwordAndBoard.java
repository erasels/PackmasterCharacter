package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.makePath;

public class SwordAndBoard extends AbstractEvenOddCard{
    public final static String ID = makeID(SwordAndBoard.class.getSimpleName());
    private static final int DAMAGE = 10;
    private static final int BLOCK = 8;
    private static final int UDAMAGE = 3;
    private static final int UBLOCK = 3;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    public SwordAndBoard() {
        super(ID, COST, TYPE, RARITY, TARGET);
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        initializeDescription();
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
    }
    
    @Override
    public void upp() {
        upgradeDamage(UDAMAGE);
        upgradeBlock(UBLOCK);
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
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1)
        {
            this.loadCardImage(makePath("images/cards/SwordAndBoard.png"));
            this.type = CardType.ATTACK;
            this.target = CardTarget.ENEMY;
        }
        else
        {
            this.loadCardImage(makePath("images/cards/SwordAndBoardSkill.png"));
            this.type = CardType.SKILL;
            this.target = CardTarget.SELF;
        }
    }
    
    @Override
    protected String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1)
        {
            return cardStrings.EXTENDED_DESCRIPTION[0]
            + cardStrings.EXTENDED_DESCRIPTION[1]
            + cardStrings.EXTENDED_DESCRIPTION[2]
            + cardStrings.EXTENDED_DESCRIPTION[3]
            + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[4]);
        }
        else
        {
            return cardStrings.EXTENDED_DESCRIPTION[0]
                + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[1])
                + cardStrings.EXTENDED_DESCRIPTION[2]
                + cardStrings.EXTENDED_DESCRIPTION[3]
                + cardStrings.EXTENDED_DESCRIPTION[4];
        }
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if ((AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1) % 2 == 1) {
                    this.addToTop(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
                }
                else
                {
                    this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
                }
                this.isDone = true;
            }
        });
    }
}
