package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.util.Wiz;

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

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.p().powers.stream()
                        .filter(p -> p.type == AbstractPower.PowerType.DEBUFF)
                        .forEach(p -> dmg(m, AttackEffect.SLASH_DIAGONAL));
                isDone = true;
            }
        });

    }

    public void upp(){
        upgradeDamage(UPGRADE_DAMAGE);
    }

    public String cardArtCopy() {
        return InfernalBlade.ID;
    }
}
