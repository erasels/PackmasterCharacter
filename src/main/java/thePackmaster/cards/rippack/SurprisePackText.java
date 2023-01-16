package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class SurprisePackText extends AbstractRippedTextCard {
    public final static String ID = makeID("SurprisePackText");

    public SurprisePackText() {
        super(ID, new SurprisePack());
        baseMagicNumber = magicNumber = 3;
    }

    public SurprisePackText(SurprisePack sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}
