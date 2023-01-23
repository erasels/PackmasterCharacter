package thePackmaster.cards.rippack;

import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
@AutoAdd.Ignore
public class SurprisePackArt extends AbstractRippedArtCard {
    public final static String ID = makeID("SurprisePackArt");

    public SurprisePackArt() {
        super(ID, new SurprisePack());
    }

    public SurprisePackArt(SurprisePack sourceCard) {
        super(ID, sourceCard);
    }
}
