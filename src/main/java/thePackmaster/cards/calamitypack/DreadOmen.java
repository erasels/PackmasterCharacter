package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonspack.JinxPower;

public class DreadOmen extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("DreadOmen");
    private static final int COST = 0;
    private static final int JINX = 5;
    private static final int UPGRADE_JINX = 2;

    public DreadOmen() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = JINX;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_JINX);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new JinxPower(m, this.magicNumber)));
    }
}
