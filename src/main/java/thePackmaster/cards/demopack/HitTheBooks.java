/*
package thePackmaster.cards.demopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class HitTheBooks extends AbstractPackmasterCard {
    public final static String ID = makeID("HitTheBooks");
    // intellij stuff skill, self, uncommon, 18, 4, , , 3, 1

    public HitTheBooks() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 18;
        baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.size() >= 7) {
            allDmg(AbstractGameAction.AttackEffect.FIRE);
        }
        else {
            atb(new DrawCardAction(magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}

 */