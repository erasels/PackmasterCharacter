package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.colorless.TheBomb;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.TheBombPower;
import thePackmaster.powers.goddessofexplosionspack.ExplosiveFormPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ExplosiveForm extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("ExplosiveForm");

    public ExplosiveForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        cardsToPreview = new TheBomb();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded)
            Wiz.atb(new ApplyPowerAction(p, p, new TheBombPower(p, 3, 40), 3));
        Wiz.atb(new ApplyPowerAction(p, p, new ExplosiveFormPower(p, magicNumber), magicNumber));
    }

    public void upp() {
    }
}

