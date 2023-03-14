package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TheSpin extends AbstractJockeyCard {
    public final static String ID = makeID("TheSpin");
    // intellij stuff attack, enemy, rare, 4, 1, , , , 

    public TheSpin() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;

        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        int count = 0;
        boolean discountedSelf = false;
        for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (q == this) {
                if (discountedSelf) {
                    count++;
                }
                else {
                    discountedSelf = true;
                }
            }
            else {
                count++;
            }
        }
        this.baseDamage += this.magicNumber * count;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int count = 0;
        boolean discountedSelf = false;
        for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (q == this) {
                if (discountedSelf) {
                    count++;
                }
                else {
                    discountedSelf = true;
                }
            }
            else {
                count++;
            }
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * count;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}
