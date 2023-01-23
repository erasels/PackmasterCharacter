package thePackmaster.cards.rippack;

import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
@AutoAdd.Ignore
public class InspirationText extends AbstractRippedTextCard {
    public final static String ID = makeID("InspirationText");

    public InspirationText() {
        super(ID, new Inspiration());
    }

    public InspirationText(Inspiration sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int artCardsInExhaust = AbstractDungeon.player.exhaustPile.group.stream().filter(card -> card instanceof AbstractRippedArtCard).collect(Collectors.toList()).size();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + artCardsInExhaust;
        if (artCardsInExhaust == 1) {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public void upp() {

    }
}
