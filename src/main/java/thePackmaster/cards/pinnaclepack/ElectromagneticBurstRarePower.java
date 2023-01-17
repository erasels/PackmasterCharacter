package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.JuggernautPower;
import thePackmaster.powers.pinnaclepack.Capacitor;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ElectromagneticBurstRarePower extends AbstractPinnacleCard {

    public final static String ID = makeID("ElectromagneticBurstRarePower");

    private static final int MAGIC = 8;
    private static final int UPGRADE_MAGIC = 4;
    private static final int MAGIC2 = 2;
    private static final int UPGRADE_MAGIC2 = 3;

    public ElectromagneticBurstRarePower() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            addToTop(new ApplyPowerAction(p, p, new Capacitor(p, secondMagic), secondMagic));
            addToBot(new ApplyPowerAction(p, p, new JuggernautPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_MAGIC2);
    }

}
