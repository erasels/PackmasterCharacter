package thePackmaster.cards.spherespack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.spherespack.ChaosGeneratorPower;

public class ChaosGenerator extends AbstractSpheresCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ChaosGenerator");
    private static final int COST = 2;
    private static final int ORBS = 1;
    private static final int UPGRADE_ORBS = 1;

    public ChaosGenerator() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = ORBS;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_ORBS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ChaosGeneratorPower(p, this.magicNumber)));
    }
}
