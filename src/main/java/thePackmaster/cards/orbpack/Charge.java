package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.AbstractPackMasterOrb;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Charge extends AbstractPackmasterCard {
    public final static String ID = makeID("Charge");
    // intellij stuff attack, enemy, rare, 6, 4, , , , 

    public Charge() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                    if (orb instanceof AbstractPackMasterOrb) {
                        ((AbstractPackMasterOrb) orb).passiveEffect();
                    } else {
                        orb.onStartOfTurn();
                        orb.onEndOfTurn();
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}