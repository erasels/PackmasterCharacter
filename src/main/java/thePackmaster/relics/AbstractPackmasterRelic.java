package thePackmaster.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.util.TexLoader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static thePackmaster.SpireAnniversary5Mod.*;

public abstract class AbstractPackmasterRelic extends CustomRelic {
    public AbstractCard.CardColor color;
    public Set<String> parentPacks;

    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, String... packIDs) {
        this(setId, tier, sfx, false, packIDs);
    }

    public AbstractPackmasterRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, boolean isShared, String... packIDs) {
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
        this.parentPacks = new HashSet<>();
        parentPacks.addAll(Arrays.asList(packIDs));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        if (parentPacks.isEmpty())
            return true;
        return SpireAnniversary5Mod.currentPoolPacks.stream().anyMatch(p -> parentPacks.contains(p.packID));
    }
}