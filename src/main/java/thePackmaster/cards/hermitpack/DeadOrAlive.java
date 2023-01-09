package thePackmaster.cards.hermitpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.hermitpack.DeadOrAliveAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DeadOrAlive extends AbstractHermitCard {
    public final static String ID = makeID("DeadOrAlive");

    private static final int DAMAGE = 8;
    private static final int DAMAGE_UP = 3;

    public DeadOrAlive() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        this.exhaust=true;
        this.tags.add(AbstractCard.CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DeadOrAliveAction(p,m,new DamageInfo(p, this.damage, this.damageTypeForTurn),this.freeToPlayOnce,this.energyOnUse));
    }

    @Override
    public void upp() {
        upgradeDamage(DAMAGE_UP);
    }
}