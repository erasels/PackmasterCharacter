package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.calamitypack.GrowingFlamesPower;

public class GrowingFlames extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GrowingFlames");
    private static final int COST = 1;
    private static final int AMOUNT = 2;
    private static final int UPGRADE_AMOUNT = 1;

    public GrowingFlames() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_AMOUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new GrowingFlamesPower(p, this.magicNumber)));
    }
}
