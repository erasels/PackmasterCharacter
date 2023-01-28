package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGrift;
import thePackmaster.onGenerateCardMidcombatInterface;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.MAGIC;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Haymaker extends AbstractDimensionalCardGrift implements onGenerateCardMidcombatInterface {
    public final static String ID = makeID("Haymaker");

    public Haymaker() {
        super(ID, 2, CardRarity.UNCOMMON, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 2;
        tags.add(MAGIC);
        isMultiDamage = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.SMASH, false);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += AbstractDungeon.actionManager.cardsPlayedThisTurn.size() * magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += AbstractDungeon.actionManager.cardsPlayedThisTurn.size() * magicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}