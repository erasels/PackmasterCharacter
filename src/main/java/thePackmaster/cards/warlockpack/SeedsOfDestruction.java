package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SeedsOfDestruction extends AbstractPackmasterCard {
    public final static String ID = makeID(SeedsOfDestruction.class.getSimpleName());

    private static final int COST = 1;
    private static final int DRAW_OPTION = -1;
    private static final int DISCARD_OPTION = -2;

    public SeedsOfDestruction() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        cardsToPreview = new Imp();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();

        AbstractPackmasterCard c1 = (AbstractPackmasterCard)this.makeCopy();
        c1.cost = -2;
        c1.magicNumber = c1.baseMagicNumber = this.magicNumber;
        c1.secondMagic = DRAW_OPTION;
        c1.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        c1.initializeDescription();

        AbstractPackmasterCard c2 = (AbstractPackmasterCard)this.makeCopy();
        c2.cost = -2;
        c2.magicNumber = c2.baseMagicNumber = this.magicNumber + 1;
        c2.secondMagic = DISCARD_OPTION;
        c2.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        c2.initializeDescription();

        choices.add(c1);
        choices.add(c2);
        this.addToBot(new ChooseOneAction(choices));
    }

    public void onChoseThisOption() {
        if (secondMagic == DRAW_OPTION)
            this.addToBot(new MakeTempCardInDrawPileAction(new Imp(), magicNumber, true, true));
        if (secondMagic == DISCARD_OPTION)
            this.addToBot(new MakeTempCardInDiscardAction(new Imp(), magicNumber));
    }

    @Override
    public void upp() {
        upMagic(1);
    }

    @Override
    public float getTitleFontSize() {
        return 20.0f;
    }
}
