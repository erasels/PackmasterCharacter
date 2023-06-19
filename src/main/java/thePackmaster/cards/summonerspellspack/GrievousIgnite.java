package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.util.Wiz.p;

public class GrievousIgnite extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GrievousIgnite");
    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int MAGIC_TWO = 5;

    public GrievousIgnite() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.secondMagic = this.baseSecondMagic = MAGIC_TWO;
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
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.secondMagic = this.baseSecondMagic = p().hand.group.size() * this.magicNumber;

        this.rawDescription = this.upgraded ?
                cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] :
                cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];

        this.initializeDescription();
    }
}
