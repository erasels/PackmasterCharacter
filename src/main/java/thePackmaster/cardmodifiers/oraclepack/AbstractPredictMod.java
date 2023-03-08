package thePackmaster.cardmodifiers.oraclepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractPredictMod extends AbstractCardModifier {

    public int amount;

    public abstract void updateAmount(AbstractCard card, int newAmount);
}
