package thePackmaster.cardmodifiers.energyandechopack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;

public class GlowEchoMod extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("GlowEchoMod");
    public AbstractCardModifier makeCopy() {
        return new GlowEchoMod();
    }
    public String identifier(AbstractCard card) {
        return ID;
    }
}
