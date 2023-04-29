package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.GhostingMaelstromPower;

public class GhostingMaelstrom extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GhostingMaelstrom");
    private static final int COST = 3;
    private static final int UPG_COST = 2;
    private static final int MAGIC = 5;


    public GhostingMaelstrom() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GhostingMaelstromPower(p, magicNumber), magicNumber));
    }
}
