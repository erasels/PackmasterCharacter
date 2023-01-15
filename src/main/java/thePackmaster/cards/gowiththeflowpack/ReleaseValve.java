package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;
import thePackmaster.powers.gowiththeflowpack.ReleaseValvePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReleaseValve extends AbstractHydrologistCard {
    public final static String ID = makeID("ReleaseValve");

    public ReleaseValve() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, Subtype.STEAM);
        magicNumber = baseMagicNumber = 4;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new ReleaseValvePower(m, p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}