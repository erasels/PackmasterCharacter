package thePackmaster.cards.intriguepack;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EtTu extends AbstractIntrigueCard {
    public final static String ID = makeID("EtTu");

    public EtTu() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        magicNumber = baseMagicNumber = 3;
        cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractCard cardy = new Shiv();
            CardModifierManager.addModifier(cardy, new RetainMod());

            this.addToBot(new MakeTempCardInHandAction(cardy, magicNumber));

            if (upgraded)
            {
                AbstractCard royal_cardy = new Shiv();
                CardModifierManager.addModifier(royal_cardy, new RetainMod());
                royal_cardy.rarity = CardRarity.RARE;
                this.addToBot(new MakeTempCardInHandAction(royal_cardy, 1));
            }
    }

    public void upp() {
    }
}
