package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.rimworldpack.PyromaniacPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Pyromaniac extends AbstractRimCard {
    public final static String ID = makeID(Pyromaniac.class.getSimpleName());

    public Pyromaniac() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new PyromaniacPower(p, 1));
    }

    public void upp() {
        isInnate = true;
    }
}