package thePackmaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.*;

public abstract class AbstractPackmasterRelic extends CustomRelic {
    public AbstractCard.CardColor color;
    public String parentPackID;

    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, String packID) {
        this(setId, tier, sfx, packID, false);
    }
    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null, false);
    }

    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, String packID, boolean isShared) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "Outline.png"));
        if (isShared) {
            if (sharedContentMode) {
                this.color = null;
            } else {
                this.color = ThePackmaster.Enums.PACKMASTER_RAINBOW;
            }
        } else {

            this.color = ThePackmaster.Enums.PACKMASTER_RAINBOW;
        }
        this.parentPackID = packID;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        if (parentPackID != null) {
            return SpireAnniversary5Mod.currentPoolPacks.stream().anyMatch(cp -> cp.packID.equals(parentPackID));
        }

        return true;
    }
}