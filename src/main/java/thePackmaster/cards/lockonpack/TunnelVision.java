package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.lockonpack.TunnelVisionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TunnelVision extends AbstractLockonCard {

    public final static String ID = makeID(TunnelVision.class.getSimpleName());

    public TunnelVision() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TunnelVisionPower(p)));
    }
}
