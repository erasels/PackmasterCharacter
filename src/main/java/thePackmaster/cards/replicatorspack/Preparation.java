package thePackmaster.cards.replicatorspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Preparation extends AbstractReplicatorCard {


    public final static String ID = makeID("Preparation");

    public Preparation() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust=true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0],
                (c) -> {
                    return c.type.equals(CardType.ATTACK) || c.type.equals(CardType.SKILL);
                }, (cards) -> {
            for (int i = 1; i <= magicNumber; i++) {
                atb(new MakeTempCardInHandAction(cards.get(0)));
            }
        }));
    }

    public void upp() {
        exhaust=false;
    }
}
