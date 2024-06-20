package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.upgradespack.SpecificUpgradeWithVfxAction;

public class HeartOfTheForge extends AbstractBlacksmithCard {

    public final static String ID = SpireAnniversary5Mod.makeID("HeartOfTheForge");

    public HeartOfTheForge() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        selfRetain = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public void upgrade() {
        upgradeMagicNumber(1);
        timesUpgraded++;
        upgraded = true;
        name = cardStrings.NAME + "+" + timesUpgraded;
        initializeTitle();
    }

    @Override
    public void onRetained() {
        addToBot(new SpecificUpgradeWithVfxAction(this));
    }

    public void upp() {
        upgrade();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }
}
