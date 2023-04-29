package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.TeleportBotPower;

public class TeleportBot extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("TeleportBot");
    private static final int COST = 1;
    private static final int MAGIC = 1;

    public TeleportBot() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TeleportBotPower(p, magicNumber), magicNumber));
    }
}
