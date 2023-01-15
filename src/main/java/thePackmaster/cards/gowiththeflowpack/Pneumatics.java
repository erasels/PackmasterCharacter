package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.gowiththeflowpack.FlowAction;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;
import thePackmaster.powers.gowiththeflowpack.PneumaticsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Pneumatics extends AbstractHydrologistCard {
    public final static String ID = makeID("Pneumatics");

    public Pneumatics() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.STEAM);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FlowAction());
        addToBot(new ApplyPowerAction(p, p, new PneumaticsPower(p, 1), 1));
    }

    public void upp() {
        exhaust = false;
    }
}