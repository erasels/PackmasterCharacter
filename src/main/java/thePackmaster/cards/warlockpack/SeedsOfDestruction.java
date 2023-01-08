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

    public SeedsOfDestruction() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        secondMagic = baseSecondMagic = 2;
        cardsToPreview = new Imp();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList();

        AbstractCard c1 = this.makeCopy();
        c1.cost = -2;
        c1.magicNumber = -1;
        c1.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];

        AbstractCard c2 = this.makeCopy();
        c2.cost = -2;
        c1.magicNumber = -2;
        c2.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];

        choices.add(c1);
        choices.add(c2);
        this.addToBot(new ChooseOneAction(choices));
    }

    public void onChoseThisOption() {
        if (magicNumber == -1)
            this.addToBot(new MakeTempCardInDrawPileAction(new Imp(), secondMagic, true, true));
        if (magicNumber == -2)
            this.addToBot(new MakeTempCardInDiscardAction(new Imp(), secondMagic + 1));
    }

    @Override
    public void upp() {
        upSecondMagic(1);
    }
}
