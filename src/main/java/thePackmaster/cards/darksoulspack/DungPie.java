package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.cards.green.Catalyst;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DungPie extends AbstractDarkSoulsCard{
    public final static String ID = makeID("DungPie");

    private static final int MAGIC = 10;
    private static final int MAGIC2 = 3;
    private static final int UPGRADE_MAGIC2 = -1;

    public DungPie(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        Wiz.applyToSelf(new PoisonPower(p, p, secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(UPGRADE_MAGIC2);
    }

    @Override
    public String cardArtCopy() {
        return Catalyst.ID;
    }
}
