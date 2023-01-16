package thePackmaster.cards.odditiespack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.odditiespack.FinalFormPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class FinalForm extends AbstractPackmasterCard {
    public final static String ID = makeID("FinalForm");
    // intellij stuff power, self, rare, , , , , , 

    public FinalForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isUnnate = true;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            for (AbstractCard c : cards) {
                applyToSelfTop(new FinalFormPower(c.makeStatEquivalentCopy()));
                att(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }));
    }

    public void upp() {
        isUnnate = false;
    }
}