package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.gowiththeflowpack.FlowAction;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;
import thePackmaster.powers.gowiththeflowpack.AccumulatorPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IcyFloe extends AbstractHydrologistCard {
    public final static String ID = makeID("IcyFloe");

    public IcyFloe() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.ICE);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AccumulatorPower(p, magicNumber), magicNumber));
        addToBot(new FlowAction());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}