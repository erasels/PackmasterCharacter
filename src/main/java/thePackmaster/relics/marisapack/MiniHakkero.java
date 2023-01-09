package thePackmaster.relics.marisapack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.MarisaPack;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

public class MiniHakkero extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("MiniHakkero");
    private static final int GAIN_AMT = 2;

    public MiniHakkero() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL, MarisaPack.ID);
    }

    @Override
    public void atTurnStart() {
        flash();
        Wiz.applyToSelf(new ChargeUpPower(GAIN_AMT));
    }

    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0],GAIN_AMT);
    }
}
