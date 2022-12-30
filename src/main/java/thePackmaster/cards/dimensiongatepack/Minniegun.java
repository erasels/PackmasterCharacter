package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Minniegun extends AbstractDimensionalCard {
    public final static String ID = makeID("Minniegun");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public Minniegun() {
        super(ID, 1, CardRarity.COMMON, AbstractCard.CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 4;
        setFrame("minniegunframe.png");

    }


    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}