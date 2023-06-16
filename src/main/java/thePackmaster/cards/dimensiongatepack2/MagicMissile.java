package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGordian;
import thePackmaster.powers.dimensiongatepack.MagicMissilePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MagicMissile extends AbstractDimensionalCardGordian {
    public final static String ID = makeID("MagicMissile");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public MagicMissile() {
        super(ID, 1, CardRarity.SPECIAL, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 5;
        
        selfRetain = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        Wiz.applyToSelf(new MagicMissilePower(p, magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}