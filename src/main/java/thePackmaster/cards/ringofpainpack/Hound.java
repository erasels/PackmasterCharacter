package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class Hound extends AbstractEvolveCard {
    public final static String ID = makeID(Hound.class.getSimpleName());

    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 1;
    private static final int WEAK = 1;

    public Hound() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = WEAK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (this.timesUpgraded >= AbstractEvolveCard.MAX_UPGRADES) {
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                applyToEnemy(mo, new WeakPower(mo, magicNumber, false));
            }
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.ATTACK) {
            evolve();
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}