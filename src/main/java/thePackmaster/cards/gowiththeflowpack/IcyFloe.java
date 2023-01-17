package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import thePackmaster.actions.gowiththeflowpack.FlowAction;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IcyFloe extends AbstractHydrologistCard {
    public final static String ID = makeID("IcyFloe");

    public IcyFloe() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.ICE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FlowAction());
        int energy = upgraded ? 3 : 2;
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, energy), energy));
    }

    public void upp() {
    }
}