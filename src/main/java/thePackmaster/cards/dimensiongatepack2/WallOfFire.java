package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGordian;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WallOfFire extends AbstractDimensionalCardGordian {
    public final static String ID = makeID("WallOfFire");

    public WallOfFire() {
        super(ID, 5, CardRarity.RARE, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new FlamePillar();

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }

        for (int i = 0; i < this.magicNumber; ++i) {
            AbstractCard c = new FlamePillar();
            if (upgraded) c.upgrade();
            addToBot(new MakeTempCardInHandAction(c, 1));
        }
    }

    public void upp() {
        upgradeDamage(2);
        cardsToPreview.upgrade();
    }
}