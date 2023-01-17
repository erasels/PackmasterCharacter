package thePackmaster.cards.psychicpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.cardmodifiers.psychicpack.UnplayableThisTurnModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OneIsEnough extends AbstractPsychicCard {
    public final static String ID = makeID("OneIsEnough");
    // intellij stuff skill, none, uncommon, , , , , 2, 1

    public OneIsEnough() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        HandSelectAction callback = new HandSelectAction(1, (c)->DrawCardAction.drawnCards.contains(c), cards -> {
            ArrayList<AbstractCard> notChosen = new ArrayList<>(DrawCardAction.drawnCards);
            notChosen.removeAll(cards);

            for (AbstractCard c : notChosen) {
                CardModifierManager.addModifier(c, new UnplayableThisTurnModifier());
            }
        }, null, cardStrings.EXTENDED_DESCRIPTION[0], true, false, false);

        addToBot(new DrawCardAction(this.magicNumber, callback));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}