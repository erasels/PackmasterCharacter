package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class LunchBreak extends AbstractPackmasterCard {

    public final static String ID = makeID(LunchBreak.class.getSimpleName());

    private static final int COST = 1;
    public LunchBreak(){
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawCardNextTurnPower(p, magicNumber));
        applyToSelf(new EnergizedBluePower(p, 1));
    }

    @Override
    public void upp() {
        upMagic(1);
    }
}
