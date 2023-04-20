package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import java.util.concurrent.atomic.AtomicInteger;

import static com.megacrit.cardcrawl.actions.GameActionManager.damageReceivedThisTurn;
import static com.megacrit.cardcrawl.actions.GameActionManager.playerHpLastTurn;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MorionBlade extends AbstractDarkSoulsCard{
    public final static String ID = makeID("MorionBlade");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 2;

    public MorionBlade(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < Wiz.countDebuffs(p) + 1; i++)
            dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp(){
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
