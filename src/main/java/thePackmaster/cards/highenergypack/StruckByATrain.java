package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class StruckByATrain extends AbstractPackmasterCard {
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
            applyToEnemy(q, new VulnerablePower(q, magicNumber, false));
        }
    }

    private static AbstractMonster getFrontmostEnemy() {
        AbstractMonster foe = null;
        float bestPos = 10000F;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDead && !m.isDying) {
                if (m.drawX < bestPos) {
                    foe = m;
                    bestPos = m.drawX;
                }
            }
        }
        return foe;
    }

    public void upp() {
        upgradeDamage(5);
        upgradeMagicNumber(1);
    }
}