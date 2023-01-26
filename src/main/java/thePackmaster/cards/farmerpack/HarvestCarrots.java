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
        baseMagicNumber = magicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = checkTypes(true);
            for (int j = 0; j < count+1; j++) {
                att(new GainBlockAction(p(), block));
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
        upgradeBlock(2);
    }
}
