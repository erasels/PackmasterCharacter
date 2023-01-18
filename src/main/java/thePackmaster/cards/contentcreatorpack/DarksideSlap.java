package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DarksideSlap extends AbstractContentCard {
    public final static String ID = makeID("DarksideSlap");
    public DarksideSlap() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 25;
    }

    //TODO - Megathereal effect

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void upp() {
        upgradeDamage(5);
    }
}