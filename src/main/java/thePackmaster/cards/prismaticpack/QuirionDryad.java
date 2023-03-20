package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.prismaticpack.QuirionDryadPower;

public class QuirionDryad extends AbstractPrismaticCard {
    public static final String ID = SpireAnniversary5Mod.makeID("QuirionDryad");
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int STATS = 1;

    public QuirionDryad() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = STATS;
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new QuirionDryadPower(p, this.magicNumber)));
    }
}
