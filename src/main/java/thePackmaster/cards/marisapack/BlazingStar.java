package thePackmaster.cards.marisapack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.marisapack.FreeAmplifyPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlazingStar  extends AbstractMarisaCard {
    public final static String ID = makeID(BlazingStar.class.getSimpleName());
    private static final int MAGIC = 2, UPG_MAGIC = 2;

    public BlazingStar() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));
        Wiz.applyToSelf(new LoseStrengthPower(p, magicNumber));

        Wiz.applyToSelf(new FreeAmplifyPower(1));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
