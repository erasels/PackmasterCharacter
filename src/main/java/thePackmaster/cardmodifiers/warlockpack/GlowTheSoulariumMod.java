package thePackmaster.cardmodifiers.warlockpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;

public class GlowTheSoulariumMod extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("GlowTheSoulariumMod");
    public AbstractCardModifier makeCopy() {
        return new GlowTheSoulariumMod();
    }
    public String identifier(AbstractCard card) {
        return ID;
    }
}
