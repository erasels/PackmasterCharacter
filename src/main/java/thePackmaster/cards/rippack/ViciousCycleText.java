package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.rippack.AbstractRippableCard.cardsRippedThisTurn;

@NoCompendium
@NoPools
public class ViciousCycleText extends AbstractRippedTextCard {
    public final static String ID = makeID("ViciousCycleText");

    public ViciousCycleText() {
        super(ID, new ViciousCycle());
        exhaust = true;
    }

    public ViciousCycleText(ViciousCycle sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + cardsRippedThisTurn;
        if (cardsRippedThisTurn == 1) {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}
