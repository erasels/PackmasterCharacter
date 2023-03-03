package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class Joust extends AbstractPackmasterCard {
    public final static String ID = makeID("Joust");
    // intellij stuff attack, enemy, uncommon, 16, 3, , , 1, 1

    public Joust() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(WeakPower.POWER_ID)) {
            return super.canUse(p, m);
        }
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}