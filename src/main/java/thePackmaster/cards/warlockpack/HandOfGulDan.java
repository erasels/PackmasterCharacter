package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class HandOfGulDan extends AbstractWarlockCard {
    public final static String ID = makeID(HandOfGulDan.class.getSimpleName());

    private static final int COST = 1;

    public HandOfGulDan() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upMagic(1);
    }
}
