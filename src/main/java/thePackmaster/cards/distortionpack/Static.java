package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.distortionpack.StaticAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Static extends AbstractDistortionCard {
    public final static String ID = makeID("Static");
    // intellij stuff attack, all_enemy, rare, 1, , , , 5, 2

    public Static() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 1;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i)
            atb(new StaticAction(p, this));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}