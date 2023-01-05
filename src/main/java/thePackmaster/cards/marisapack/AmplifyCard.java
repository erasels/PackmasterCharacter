package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.patches.marisapack.AmplifyPatches;
import thePackmaster.util.Wiz;

public interface AmplifyCard {
    //Amplify glow is handled in the main mod file receivePostInitialize by adding the GlowInfo
    Color AMPLIFY_GLOW_COLOR = Color.WHITE;

    /**
     * @param thisCard Instance of AbstractCard that should only ever be the card this interface instance is on
     *                 Example: ((AmplifyCard)c).shouldAmplify(c)
     * @return boolean that is true if the card would be amplified if it is played now.
     *                 This should be used in applyPowers/calculateCardDamage
     */
    default boolean shouldAmplify(AbstractCard thisCard) {
        int cardCost = Wiz.getLogicalCardCost(thisCard);
        return EnergyPanel.totalCount >= cardCost + ((AmplifyCard)thisCard).getAmplifyCost();
    }


    /**
     * @param thisCard Instance of AbstractCard that should only ever be the card this interface instance is on
     *                 Example: ((AmplifyCard)c).isAmplified(c)
     * @return boolean that is true if the card is amplified in AbstractPlayer.useCard.
     *                 Can be used in the card's use method although useAmplified/skipOnAmplify system is preferred.
     */
    default boolean isAmplified(AbstractCard thisCard) {
        return AmplifyPatches.amplified == thisCard;
    }

    //When true, skips card's normal use method if amplify is activated
    boolean skipUseOnAmplify();

    //The use method that gets executed when an amplified card is played, happens after a card's normal use
    void useAmplified(AbstractPlayer p, AbstractMonster m);

    //Defines the amount of additional energy you need to activate the Amplify effect (on top of the card's energy cost)
    int getAmplifyCost();
}
