package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.CheapenModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Rummage;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LoseIt extends AbstractPackmasterCard {
    public final static String ID = makeID("LoseIt");

    public LoseIt() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "basics");
        this.exhaust = true;
        this.cardsToPreview = new Rummage();
        this.cardsToPreview.costForTurn = this.cardsToPreview.cost = 1;
        this.cardsToPreview.isCostModified = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new MakeTempCardInHandAction(cardsToPreview));
        for(AbstractCard c : p.hand.group)
            if (c.cardID.equals(Rummage.ID) && c.cost > this.magicNumber) {
                c.costForTurn = c.cost = this.magicNumber;
                c.isCostModified = true;
            }
        for(AbstractCard c : p.drawPile.group)
            if (c.cardID.equals(Rummage.ID) && c.cost > this.magicNumber) {
                c.costForTurn = c.cost = this.magicNumber;
                c.isCostModified = true;
            }
        for(AbstractCard c : p.discardPile.group)
            if (c.cardID.equals(Rummage.ID) && c.cost > this.magicNumber) {
                c.costForTurn = c.cost = this.magicNumber;
                c.isCostModified = true;
            }
    }

    public void upp(){
        this.cardsToPreview.upgrade();
    }
}
