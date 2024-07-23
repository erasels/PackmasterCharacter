package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.upgradespack.ExhaustRandomPredicateCardAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CarelessSwing extends AbstractBlacksmithCard {

    public final static String ID = makeID("CarelessSwing");

    public CarelessSwing() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new ExhaustRandomPredicateCardAction(c -> !c.upgraded, Wiz.adp().hand));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
