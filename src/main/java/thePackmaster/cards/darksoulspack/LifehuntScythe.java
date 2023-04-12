package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LifehuntScythe extends AbstractDarkSoulsCard{
    public final static String ID = makeID("LifehuntScythe");

    private static final int DAMAGE = 25;
    private static final int MAGIC = 6;
    private static final int UPGRADE_MAGIC = -1;

    public LifehuntScythe(){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        Wiz.applyToSelf(new FrailPower(p, magicNumber, false));
    }

    public void upp(){
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    @Override
    public String cardArtCopy() {
        return Cleave.ID;
    }
}