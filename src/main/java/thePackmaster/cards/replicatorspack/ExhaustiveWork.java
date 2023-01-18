package thePackmaster.cards.replicatorspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ExhaustiveWork extends AbstractReplicatorCard {


    public final static String ID = makeID("ExhaustiveWork");

    public ExhaustiveWork() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
        atb(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0],
                (c) -> {
                    return true;
                }, (cards) -> {
            for (int i = 1; i <= 1; i++) {
                atb(new MakeTempCardInDrawPileAction(cards.get(0), 1, true, true));
                atb(new ExhaustSpecificCardAction(cards.get(0), p.hand, false));
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
