package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface AmplifyCard {
    //Amplify glow is handled in the main mod file receivePostInitialize by adding the GlowInfo
    Color AMPLIFY_GLOW_COLOR = Color.WHITE;

    //When true, skips card's normal use method if amplify is activated
    boolean skipUseOnAmplify();

    //The use method that gets executed when an amplified card is played, happens after a card's normal use
    void useAmplified(AbstractPlayer p, AbstractMonster m);

    //Defines the amount of additional energy you need to activate the Amplify effect (on top of the card's energy cost)
    int getAmplifyCost();
}
