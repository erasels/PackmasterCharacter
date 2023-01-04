package thePackmaster.cards.downfallpack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.downfallpack.ChronoBoostPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Chronoboost extends AbstractPackmasterCard {
    public final static String ID = makeID("Chronoboost");

    public Chronoboost() {
        super(ID, 1, AbstractCard.CardType.POWER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
        atb(new ApplyPowerAction(p, p, new ChronoBoostPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }


}


