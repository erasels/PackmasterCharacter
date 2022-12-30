package thePackmaster.relics;

import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CollectorBadge extends AbstractPackmasterRelic {
    public static final String ID = makeID("CollectorBadge");

    public CollectorBadge() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
