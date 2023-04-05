package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LifehuntScythe extends AbstractDarkSoulsCard{
    public final static String ID = makeID("LifehuntScythe");

    private static final int DAMAGE = 10;
    private static final int UPGRADE_DAMAGE = 4;

    public LifehuntScythe(){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        Wiz.atb(new LoseHPAction(p,p,6));
    }

    public void upp(){
        upgradeDamage(UPGRADE_DAMAGE);
    }

}
