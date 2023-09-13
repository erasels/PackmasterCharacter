package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Flourish extends AbstractEvenOddCard{
    public final static String ID = makeID(Flourish.class.getSimpleName());
    private static final int MAGIC = 3;
    private static final int UMAGIC = 1;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    
    public Flourish() {
        super(ID, COST, TYPE, RARITY, TARGET);
        tags.add(CardTags.HEALING);
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        magicNumber = baseMagicNumber = MAGIC;
        initializeDescription();
    }
    
    @Override
    public void upp() {
        upgradeMagicNumber(UMAGIC);
    }
    
    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        initializeDescription();
    }
    
    @Override
    protected String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0)
        {
            return cardStrings.DESCRIPTION +
                    cardStrings.EXTENDED_DESCRIPTION[0]+
                    cardStrings.EXTENDED_DESCRIPTION[1]+
                    AbstractEvenOddCard.makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[2])+
                    cardStrings.EXTENDED_DESCRIPTION[0]+
                    cardStrings.EXTENDED_DESCRIPTION[3]+
                    cardStrings.EXTENDED_DESCRIPTION[4];
        }
        else
        {
            return cardStrings.DESCRIPTION +
                    cardStrings.EXTENDED_DESCRIPTION[0]+
                    cardStrings.EXTENDED_DESCRIPTION[1]+
                    cardStrings.EXTENDED_DESCRIPTION[2]+
                    cardStrings.EXTENDED_DESCRIPTION[0]+
                    cardStrings.EXTENDED_DESCRIPTION[3]+
                    AbstractEvenOddCard.makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[4]);
        }
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.applyToSelf(new PlatedArmorPower(AbstractDungeon.player, magicNumber));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0) {
                this.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
            }
            else
            {
                this.addToTop(new GainEnergyAction(1));
            }
                this.isDone = true;
        
            }
        });
    }
}
