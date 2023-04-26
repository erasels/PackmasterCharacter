package thePackmaster.cards.basicspack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Rummage;
import thePackmaster.cardmodifiers.basicspack.MagicModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LoseIt extends AbstractPackmasterCard {
    public final static String ID = makeID("LoseIt");

    public LoseIt() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "basics");
        this.exhaust = true;
        this.cardsToPreview = new Rummage();
        this.baseMagicNumber = this.magicNumber = 1;
        CardModifierManager.addModifier(cardsToPreview, new MagicModifier(this.magicNumber, true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractCard c : p.hand.group)
            if (c.cardID.equals(Rummage.ID))
                CardModifierManager.addModifier(c, new MagicModifier(this.magicNumber, true));
        for(AbstractCard c : p.drawPile.group)
            if (c.cardID.equals(Rummage.ID))
                CardModifierManager.addModifier(c, new MagicModifier(this.magicNumber, true));
        for(AbstractCard c : p.discardPile.group)
            if (c.cardID.equals(Rummage.ID))
                CardModifierManager.addModifier(c, new MagicModifier(this.magicNumber, true));
        addToTop(new MakeTempCardInHandAction(cardsToPreview));
    }

    public void upp(){
        this.cardsToPreview.upgrade();
    }
}
