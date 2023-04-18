package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.powers.summonerspellspack.AntihealPower;

public class GrievousIgnite extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GrievousIgnite");
    private static final int COST = 1;
    private static final int MAGIC = 1;

    public GrievousIgnite() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void upp() {
        exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = p.hand.group.size() * this.magicNumber;

        addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, amt), amt));
        addToBot(new ApplyPowerAction(m, p, new AntihealPower(m, amt), amt));
    }
}
