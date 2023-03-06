package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.secretlevelpack.DanseMacabrePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class DanseMacabre extends AbstractSecretLevelCard {
    public final static String ID = makeID("DanseMacabre");
    // intellij stuff power, self, uncommon, , , , , 6, 3

    public DanseMacabre() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateHopAction(p));
        applyToSelf(new DanseMacabrePower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}