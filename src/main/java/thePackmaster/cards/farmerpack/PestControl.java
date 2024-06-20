package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PestControl extends AbstractFarmerCard {
    public final static String ID = makeID("PestControl");
    public PestControl() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = checkTypes(true);
        for (int j = 0; j < count+1; j++) {
            dmg(m, AbstractGameAction.AttackEffect.POISON);
        }
    }
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = this.magicNumber = checkTypes(false);
        this.isMagicNumberModified = false;
        super.calculateCardDamage(mo);

        if (this.baseMagicNumber > 0) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeDescription();
        }
        else {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }
    public void applyPowers() {
        this.baseMagicNumber = this.magicNumber = checkTypes(false);
        this.isMagicNumberModified = false;
        super.applyPowers();

        if (this.baseMagicNumber > 0) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeDescription();
        }
        else {
            this.rawDescription = cardStrings.DESCRIPTION;
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

