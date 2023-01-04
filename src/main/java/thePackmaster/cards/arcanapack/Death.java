package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.arcanapack.KillEnemyAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Death extends AbstractAstrologerCard {
    public final static String ID = makeID("Death");
    // intellij stuff attack, enemy, uncommon, 5, 3, , , , 

    public Death() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            boolean probablyKill = m.currentHealth - damage <= (m.maxHealth / 10);
            dmg(m, probablyKill ? AbstractGameAction.AttackEffect.SLASH_HEAVY : AbstractGameAction.AttackEffect.SLASH_DIAGONAL);

            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (m.currentHealth <= (m.maxHealth / 10)) {
                        atb(new KillEnemyAction(m));
                    }
                }
            });
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}