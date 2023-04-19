package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;

public class ChillingCurse extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ChillingCurse");
    private static final int COST = 0;
    private static final int FROSTBITE_WEAK = 2;
    private static final int UPGRADE_FROSTBITE_WEAK = 1;

    public ChillingCurse() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = FROSTBITE_WEAK;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_FROSTBITE_WEAK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new FrostbitePower(m, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
    }
}
