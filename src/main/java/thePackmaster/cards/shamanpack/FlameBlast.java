package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.shamanpack.IgnitePower;

public class FlameBlast extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FlameBlast");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int IGNITE = 4;
    private static final int VULNERABLE = 2;
    private static final int UPGRADE_VULNERABLE = 1;

    public FlameBlast() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = IGNITE;
        this.secondMagic = this.baseSecondMagic = VULNERABLE;
    }

    @Override
    public void upp() {
        this.upgradeSecondMagic(UPGRADE_VULNERABLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));
        if (m != null && m.hasPower(VulnerablePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));
        }
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.secondMagic, false)));
    }
}
