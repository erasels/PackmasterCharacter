package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class Flashlight extends AbstractEchoCard {

    public final static String ID = makeID(Flashlight.class.getSimpleName());

    private static final int COST = 0;

    public Flashlight(){
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        isInnate = true;
        exhaust = true;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
        if (upgraded)
            applyToSelf(new EnergizedBluePower(p, 1));
    }

    @Override
    public void upp() {
    }
}
