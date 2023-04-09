package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import static thePackmaster.util.Wiz.p;

public class HexflashCard extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Hexflash");
    private static final int COST = 0;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    public static final int TIMER_MAX = 3;
    public int timer = 3;

    public HexflashCard() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        magicNumber = baseMagicNumber = MAGIC;
        exhaust = true;
        selfRetain = true;
        timer = TIMER_MAX;
        //cardsToPreview = new FlashCard();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, magicNumber));
    }

    @Override
    public void onRetained() {
        timer--;
        if (timer < 1) {
            this.addToBot(new ExhaustSpecificCardAction(this, p().hand, true));

            AbstractCard c = new FlashCard();
            if (upgraded)
                c.upgrade();
            this.addToBot(new MakeTempCardInHandAction(c, 1));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        this.rawDescription =
                cardStrings.DESCRIPTION +
                cardStrings.EXTENDED_DESCRIPTION[0] +
                timer +
                (timer == 1 ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[2]);
        this.initializeDescription();
    }
}
