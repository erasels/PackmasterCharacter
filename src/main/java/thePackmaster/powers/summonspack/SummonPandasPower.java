package thePackmaster.powers.summonspack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.summonspack.Panda;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.removePower;

public class SummonPandasPower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(SummonPandasPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static int summonIdOffset;

    public SummonPandasPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        ID = POWER_ID + summonIdOffset;
        summonIdOffset++;
    }

    @Override
    public void atStartOfTurn() {
        atb(new ChannelAction(new Panda(amount)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    static {
        summonIdOffset = 0;
    }
}