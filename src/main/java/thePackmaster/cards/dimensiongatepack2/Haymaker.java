package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGrift;
import thePackmaster.util.creativitypack.onGenerateCardMidcombatInterface;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Haymaker extends AbstractDimensionalCardGrift implements onGenerateCardMidcombatInterface {
    public final static String ID = makeID("Haymaker");
    private boolean cardBeingPlayed;

    public Haymaker() {
        super(ID, 2, CardRarity.UNCOMMON, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        cardBeingPlayed = true;
        calculateCardDamage(m);
        Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.SMASH, false);
        cardBeingPlayed = false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - (cardBeingPlayed ? 1 : 0)) * magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }


    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - (cardBeingPlayed ? 1 : 0)) * magicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}