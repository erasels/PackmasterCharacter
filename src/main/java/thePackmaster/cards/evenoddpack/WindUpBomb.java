package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.makePath;

public class WindUpBomb extends AbstractEvenOddCard{
    public final static String ID = makeID(WindUpBomb.class.getSimpleName());
    private static final int DAMAGE = 8;
    private static final int MAGIC = 15;
    private static final int UMAGIC = 5;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
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
            this.loadCardImage(makePath("images/cards/WindUpBomb.png"));
            this.target = CardTarget.ENEMY;
            this.type = CardType.ATTACK;

        }
        else
        {
            this.loadCardImage(makePath("images/cards/WindUpBombSkill.png"));
            this.target = CardTarget.NONE;
            this.type = CardType.SKILL;
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
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1) {
                    this.addToTop(new DamageAction(abstractMonster,  new DamageInfo(abstractMonster, damage, damageTypeForTurn), AttackEffect.FIRE));
                }
                else
                {
                    this.addToTop(new ModifyDamageAction(uuid, magicNumber));
                }
                this.isDone = true;
            }
        });
    }
}