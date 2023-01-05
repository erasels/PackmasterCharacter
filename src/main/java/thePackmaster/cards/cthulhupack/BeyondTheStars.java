package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.dimensiongatepack.AbstractDimensionalCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BeyondTheStars extends AbstractPackmasterCard {
    public final static String ID = makeID("BeyondTheStars");

    public BeyondTheStars() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
        isMultiDamage = true;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);

        //TODO: Entropy
        //Wiz.applyToSelf(new );


    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}