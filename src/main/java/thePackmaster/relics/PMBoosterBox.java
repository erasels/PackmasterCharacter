package thePackmaster.relics;

import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PMBoosterBox extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMBoosterBox");

    public PMBoosterBox() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
