package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Topple extends AbstractIntrigueCard {
    public final static String ID = makeID("Topple");

    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Topple() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += countCards();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += countCards();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public int countCards() {
        int counter = 0;
        Iterator var1 = AbstractDungeon.actionManager.cardsPlayedThisCombat.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (isMundane(c)) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
                Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
    }
}