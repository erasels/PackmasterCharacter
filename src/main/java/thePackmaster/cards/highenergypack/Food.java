package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

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
        if (magicNumber > 0) {
            atb(new TalkAction(true, cardStrings.EXTENDED_DESCRIPTION[4], 1F, 2F));
            atb(new GainEnergyAction(magicNumber));
        }
    }

    public void setX(int amount) {
        this.magicNumber = amount;
        this.baseMagicNumber = this.magicNumber;

        if (amount == 0) {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[3];
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(upgraded ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0]);
            for (int i = 0; i < magicNumber; i++) {
                sb.append("[E] ");
            }
            sb.append(cardStrings.EXTENDED_DESCRIPTION[2]);
            rawDescription = sb.toString();
        }
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        //Compatibility with in-combat upgrading, otherwise description says gain X [e]
        setX(magicNumber);
    }

    public void upp() {
        selfRetain = true;
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        card.baseMagicNumber = this.baseMagicNumber;
        card.magicNumber = this.magicNumber;
        card.rawDescription = this.rawDescription;
        card.initializeDescription();
        return card;
    }
}