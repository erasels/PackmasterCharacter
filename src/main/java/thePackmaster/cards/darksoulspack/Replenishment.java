package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.darksoulspack.ReplenishmentPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Replenishment extends AbstractDarkSoulsCard{
    public final static String ID = makeID("Replenishment");

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public Replenishment(){
        super(ID,1,CardType.POWER,CardRarity.UNCOMMON,CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        Wiz.applyToSelf(new ReplenishmentPower(p, magicNumber));
    }

    public void upp(){
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

}
