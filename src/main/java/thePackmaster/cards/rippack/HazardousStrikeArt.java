package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class HazardousStrikeArt extends AbstractRippedArtCard {
    public final static String ID = makeID("HazardousStrikeArt");

    public HazardousStrikeArt() {
        super(ID, new HazardousStrike());
        tags.add(CardTags.STRIKE);
    }

    public HazardousStrikeArt(HazardousStrike sourceCard) {
        super(ID, sourceCard);
        tags.add(CardTags.STRIKE);
    }
}
