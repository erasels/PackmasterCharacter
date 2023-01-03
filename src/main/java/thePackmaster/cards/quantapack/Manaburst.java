package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.quantapack.ManaburstAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Manaburst extends AbstractPackmasterCard {
    public final static String ID = makeID("Manaburst");

    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int CARD_AMOUNT = 1;
    private static final int UPGRADE_AMOUNT = 1;

    public Manaburst() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = CARD_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.addToBot(new ManaburstAction(this.magicNumber, CardType.ATTACK));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_AMOUNT);
    }
}
