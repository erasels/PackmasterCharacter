package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class HarvestRadishes extends AbstractFarmerCard {
    public final static String ID = makeID("HarvestRadishes");

    public HarvestRadishes() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = checkTypes(true);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToEnemy(m, new WeakPower(m, count, false));
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
        upgradeDamage(5);
    }
}
