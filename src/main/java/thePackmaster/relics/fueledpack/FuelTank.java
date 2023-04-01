package thePackmaster.relics.fueledpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.*;
import thePackmaster.relics.AbstractPackmasterRelic;

// code in ConsumeCardsAction
public class FuelTank extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID(FuelTank.class.getSimpleName());

    public FuelTank() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, FueledPack.ID);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
