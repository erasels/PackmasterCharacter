package thePackmaster.cards.infestpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class BugOut extends AbstractInfestCard {
    public final static String ID = makeID("BugOut");
    // intellij stuff power, self, uncommon, , , , , , 

    public BugOut() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int infestAmount = InfestModifier.getInfestCount(this);
        if (infestAmount > 0) {
            applyToSelf(new StrengthPower(p, infestAmount));
            applyToSelf(new DexterityPower(p, infestAmount));
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}