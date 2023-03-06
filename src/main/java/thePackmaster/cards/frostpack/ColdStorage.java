package thePackmaster.cards.frostpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.frostpack.ColdStoragePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ColdStorage extends AbstractFrostCard {
    public final static String ID = makeID("ColdStorage");

    public ColdStorage() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ColdStoragePower(p, this.magicNumber));
    }

    public void upp() {
        this.isInnate = true;
    }
}