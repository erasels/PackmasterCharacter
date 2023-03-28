package thePackmaster.relics.fueledpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.*;
import thePackmaster.relics.AbstractPackmasterRelic;

// code in ConsumeCardsAction
public class FuelTank extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID(FuelTank.class.getSimpleName());

    public FuelTank() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, SummonsPack.ID, OrbPack.ID, DefectPack.ID, SpheresPack.ID, FrostPack.ID);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
