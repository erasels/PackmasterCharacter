package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonerspellspack.SpiritBarrierAction;

public class SpiritBarrier extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SpiritBarrier");
    private static final int COST = 1;
    private static final int UPG_COST = 0;

    public SpiritBarrier() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SpiritBarrierAction());
    }
}
