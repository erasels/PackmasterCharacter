package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonerspellspack.ClutchTeleportAction;
import thePackmaster.actions.summonerspellspack.WitheringExhaustAction;

public class ClutchTeleportCard extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ClutchTeleport");
    private static final int COST = 1;
    private static final int UPG_COST = 0;

    public ClutchTeleportCard() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ClutchTeleportAction());
    }
}
