package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Food extends AbstractPackmasterCard {
    public final static String ID = makeID("Food");
    // intellij stuff skill, self, special, , , , , , 

    public Food() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
    }

    public void setX(int amount) {
        this.magicNumber = amount;
        this.baseMagicNumber = this.magicNumber;

        if (amount == 0) {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[3];
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(upgraded ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0]);
            for (int i = 0; i < magicNumber; i++) {
                sb.append(" [E] ");
            }
            sb.append(cardStrings.EXTENDED_DESCRIPTION[2]);
            rawDescription = sb.toString();
            this.initializeDescription();
        }
    }

    public void upp() {
        selfRetain = true;
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        card.baseMagicNumber = this.baseMagicNumber;
        card.magicNumber = this.magicNumber;
        card.description = (ArrayList) this.description.clone();
        return card;
    }
}