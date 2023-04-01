package thePackmaster.cards.utilitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Plasma;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.utilitypack.PlasmaShieldPower;

public class PlasmaShield extends AbstractUtilityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("PlasmaShield");
    private static final int COST = 2;
    private static final int AMOUNT = 2;
    private static final int UPGRADE_AMOUNT = 2;
    private static final int PLASMA = 1;

    public PlasmaShield() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
        this.secondMagic = this.baseSecondMagic = PLASMA;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_AMOUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PlasmaShieldPower(p, this.magicNumber)));
        this.addToBot(new ChannelAction(new Plasma()));
    }
}
