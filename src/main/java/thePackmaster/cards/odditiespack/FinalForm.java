package thePackmaster.cards.odditiespack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.odditiespack.FinalFormPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class FinalForm extends AbstractOdditiesCard {
    public final static String ID = makeID("FinalForm");
    // intellij stuff power, self, rare, , , , , , 

    public FinalForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractCard target = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                    applyToSelfTop(new FinalFormPower(target.makeStatEquivalentCopy()));
                    att(new ExhaustSpecificCardAction(target, AbstractDungeon.player.hand));
                }
            });
        } else {
            atb(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
                for (AbstractCard c : cards) {
                    applyToSelfTop(new FinalFormPower(c.makeStatEquivalentCopy()));
                    att(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }));
        }
    }

    public void upp() {
    }
}