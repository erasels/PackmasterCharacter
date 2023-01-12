package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class ArtAttackText extends AbstractRippedTextCard {
    public final static String ID = makeID("ArtAttackText");

    public ArtAttackText() {
        super(ID, new ArtAttack());
    }

    public ArtAttackText(ArtAttack sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void upp() {
    }
}
