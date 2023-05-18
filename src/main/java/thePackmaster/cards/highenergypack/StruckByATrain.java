package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.getFrontmostEnemy;

public class StruckByATrain extends AbstractHighEnergyCard {
    public final static String ID = makeID("StruckByATrain");
    // intellij stuff attack, enemy, uncommon, 25, 5, , , 2, 1

    public StruckByATrain() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 24;
        baseMagicNumber = magicNumber = 24;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster q = getFrontmostEnemy();
        if (q != null) {
            calculateCardDamage(q);
            dmg(q, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
        atb(new ModifyDamageAction(this.uuid, this.magicNumber));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(4);
    }
}