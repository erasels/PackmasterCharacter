package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PestControl extends AbstractFarmerCard {
    public final static String ID = makeID("PestControl");
    public PestControl() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = checkTypes(true);
        for (int j = 0; j < count+1; j++) {
            dmg(m, AbstractGameAction.AttackEffect.POISON);
        }
    }
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }
    public void applyPowers() {
        int count = checkTypes(false);
        if (count > 0) {
            this.baseMagicNumber = count;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeDescription();
        }
    }
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }


    public void upp() {
        upgradeDamage(2);
    }
}

