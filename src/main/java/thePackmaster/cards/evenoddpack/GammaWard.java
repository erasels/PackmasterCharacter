package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.evenoddpack.GammaWardPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GammaWard extends AbstractEvenOddCard {
    public final static String ID = makeID(GammaWard.class.getSimpleName());
    private static final int MAGIC = 12;
    private static final int UMAGIC = 4;
    private static final int BLOCK = 4;
    private static final int UBLOCK = 2;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    
    public GammaWard() {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        baseBlock = BLOCK;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0]
                + cardStrings.EXTENDED_DESCRIPTION[1]
                + cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
    }
    
    @Override
    public void upp() {
        upgradeMagicNumber(UMAGIC);
        upgradeBlock(UBLOCK);
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0) {
                    Wiz.applyToSelf(new GammaWardPower(AbstractDungeon.player, magicNumber));
                }
                this.isDone = true;
            }
        });
    }
    
    @Override
    protected String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1)
        {
            return  cardStrings.DESCRIPTION
                    + cardStrings.EXTENDED_DESCRIPTION[0]
                    + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        else
        {
            return cardStrings.DESCRIPTION;
        }
    }
}
