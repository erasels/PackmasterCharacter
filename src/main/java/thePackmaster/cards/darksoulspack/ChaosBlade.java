package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChaosBlade extends AbstractDarkSoulsCard{
    public final static String ID = makeID("ChaosBlade");

    private static final int DAMAGE = 13;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int MAGIC = 2;

    public ChaosBlade(){
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m){
        for (int i = 0; i < Wiz.getEnemies().size(); i++) {
            Wiz.atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            Wiz.atb(new LoseHPAction(p, p, magicNumber));
        }
    }

    public void upp(){
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
