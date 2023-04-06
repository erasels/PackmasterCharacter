package thePackmaster.cards.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.actions.GameActionManager.damageReceivedThisTurn;
import static com.megacrit.cardcrawl.actions.GameActionManager.playerHpLastTurn;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MorionBlade extends AbstractDarkSoulsCard{
    public final static String ID = makeID("MorionBlade");

    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 2;

    public MorionBlade(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
    }

    ///NEED TO MAKE TRIGGER IN ENEMY TURN AS WELL

    public boolean wasHPLost(AbstractPlayer p){
        return damageReceivedThisTurn > 0 || playerHpLastTurn > p.currentHealth;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if(wasHPLost(p)){
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        }
        else {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    public void upp(){
        upgradeDamage(UPGRADE_DAMAGE);
    }

    public String cardArtCopy() {
        return InfernalBlade.ID;
    }
}
