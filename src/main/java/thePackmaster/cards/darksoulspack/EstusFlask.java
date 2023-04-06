package thePackmaster.cards.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.green.Alchemize;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EstusFlask extends AbstractDarkSoulsCard{
    public final static String ID = makeID("EstusFlask");

    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC=2;

    public EstusFlask(){
        super(ID,1,CardType.SKILL,CardRarity.COMMON,CardTarget.SELF);
        baseMagicNumber=magicNumber=MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        Wiz.atb(new AddTemporaryHPAction(p, p, magicNumber));
    }

    public void upp(){
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    @Override
    public String cardArtCopy() {
        return Alchemize.ID;
    }
}
