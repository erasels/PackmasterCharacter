package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.GhostedPower;
import thePackmaster.powers.summonerspellspack.MaelstromPower;

public class GhostingMaelstrom extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GhostingMaelstrom");
    private static final int COST = 2;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final int MAGICTWO = 2;

    public GhostingMaelstrom() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
        secondMagic = baseSecondMagic = MAGICTWO;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MaelstromPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new GhostedPower(p, secondMagic), secondMagic));
    }
}
