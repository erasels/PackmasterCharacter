package thePackmaster.powers.marisapack;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface AmplifyPowerHook {
    //Triggers right after the amplify cost is subtracted from your energy which happens after cards normally drain your energy
    void onAmplify(AbstractCard c);
}
