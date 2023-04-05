package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.cards.green.Catalyst;
import com.megacrit.cardcrawl.cards.green.DeadlyPoison;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DungPie extends AbstractDarkSoulsCard{
    public final static String ID = makeID("DungPie");

    private static final int MAGIC = 10;
    private static final int UPGRADE_MAGIC = 4;

    public DungPie(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        Wiz.applyToSelf(new PoisonPower(p, p, 2));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    @Override
    public String cardArtCopy() {
        return Catalyst.ID;
    }
}
