package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.powers.summonspack.JinxPower;

public class Suffer extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Suffer");
    private static final int COST = 1;
    private static final int DEBUFFS = 2;
    private static final int UPGRADE_DEBUFFS = 1;

    public Suffer() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = DEBUFFS;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_DEBUFFS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(m, p, new FrostbitePower(m, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(m, p, new JinxPower(m, this.magicNumber)));
    }
}
