package thePackmaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeRelicPath;
import static thePackmaster.SpireAnniversary5Mod.modID;

public abstract class AbstractPackmasterRelic extends CustomRelic {
    public AbstractCard.CardColor color;
    public String parentPackID;

    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null);
    }

    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        this(setId, tier, sfx, color, null);
    }

    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color, String packID) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "Outline.png"));
        this.color = color;
        this.parentPackID = packID;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        if(parentPackID != null) {
            return SpireAnniversary5Mod.currentPoolPacks.stream().anyMatch(cp -> cp.packID.equals(parentPackID));
        }

        return true;
    }
}