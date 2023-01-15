package thePackmaster.cardmodifiers.sneckopack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.patches.sneckopack.EnergyCountPatch;

public class GulpModifier extends AbstractCardModifier {

    public static final String ID = SpireAnniversary5Mod.makeID("GulpModifier");

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public float modifyBaseMagic(float magic, AbstractCard card) {
        return magic + EnergyCountPatch.energySpentThisCombat;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GulpModifier();
    }
}
