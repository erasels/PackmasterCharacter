package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonerspellspack.PerfectClarityAction;
import thePackmaster.powers.summonerspellspack.GhostedPower;

public class PerfectClarityCard extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("PerfectClarity");
    private static final int COST = 2;
    private static final int UPG_COST = 1;

    public PerfectClarityCard() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PerfectClarityAction());
    }
}
