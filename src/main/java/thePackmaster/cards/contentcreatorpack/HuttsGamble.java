package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.contentcreatorpack.HuttsGambleDrawPower;
import thePackmaster.powers.contentcreatorpack.HuttsGambleEnergyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class HuttsGamble extends AbstractContentCard {
    public final static String ID = makeID("HuttsGamble");

    public HuttsGamble() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HuttsGambleEnergyPower(magicNumber));
        applyToSelf(new HuttsGambleDrawPower(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}