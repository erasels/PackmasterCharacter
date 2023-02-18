package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class HarvestCarrots extends AbstractFarmerCard {
    public final static String ID = makeID("HarvestCarrots");
    public HarvestCarrots() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 4;
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = checkTypes(true);
        for (int j = 0; j < count+1; j++) {
            att(new GainBlockAction(p(), block));
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
        upgradeBlock(2);
    }
}
