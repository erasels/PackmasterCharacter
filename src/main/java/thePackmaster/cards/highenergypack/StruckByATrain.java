package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class StruckByATrain extends AbstractHighEnergyCard {
    public final static String ID = makeID("StruckByATrain");
    // intellij stuff attack, enemy, uncommon, 25, 5, , , 2, 1

    public StruckByATrain() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 25;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster q = getFrontmostEnemy();
        if (q != null) {
            dmg(q, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
        atb(new ModifyDamageAction(this.uuid, this.magicNumber));
    }

    private static AbstractMonster getFrontmostEnemy() {
        AbstractMonster foe = null;
        float bestPos = 10000F;
        for (AbstractMonster m : Wiz.getEnemies()) {
            if (m.drawX < bestPos) {
                foe = m;
                bestPos = m.drawX;
            }
        }
        return foe;
    }

    public void upp() {
        upgradeDamage(5);
        upgradeMagicNumber(5);
    }
}