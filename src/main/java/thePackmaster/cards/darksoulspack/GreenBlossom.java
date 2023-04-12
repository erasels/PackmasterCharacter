package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.darksoulspack.GreenBlossomPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GreenBlossom extends AbstractDarkSoulsCard{
    public static final String ID = makeID("GreenBlossom");

    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    public GreenBlossom(){
        super(ID,1,CardType.SKILL,CardRarity.UNCOMMON,CardTarget.SELF);
        baseMagicNumber=magicNumber=MAGIC;
        exhaust=true;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        Wiz.applyToSelf(new GreenBlossomPower(p, magicNumber));
    }

    public void upp(){
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

}
