package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardEden;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Minniegun extends AbstractDimensionalCardEden {
    public final static String ID = makeID("Minniegun");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public Minniegun() {
        super(ID, 1, CardRarity.COMMON, AbstractCard.CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 4;
        cardsToPreview = new Jam();

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
        Wiz.atb(new MakeTempCardInDrawPileAction(new Jam(), 1, true, true));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}