package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.AggressiveTeleportPower;
import thePackmaster.powers.summonerspellspack.GhostedPower;
import thePackmaster.powers.summonerspellspack.MaelstromPower;

public class AggressiveTeleport extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("AggressiveTeleport");
    private static final int COST = 2;
    private static final int MAGIC = 1;

    public AggressiveTeleport() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AggressiveTeleportPower(p, magicNumber), magicNumber));
    }
}
