package thePackmaster.cardmodifiers.warlockpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class GlowTheSoulariumMod extends AbstractCardModifier {
    public static String ID = "anniv5:GlowTheSoulariumMod";
    public AbstractCardModifier makeCopy() {
        return new GlowTheSoulariumMod();
    }
    public String identifier(AbstractCard card) {
        return ID;
    }
}
