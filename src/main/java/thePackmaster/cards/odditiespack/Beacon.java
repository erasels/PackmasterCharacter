package thePackmaster.cards.odditiespack;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Beacon extends AbstractOdditiesCard {
    public final static String ID = makeID("Beacon");
    // intellij stuff skill, self, common, , , 5, 3, , 

    public Beacon() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       atb(new SelectCardsInHandAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
           for (AbstractCard c : cards) {
               att(new AbstractGameAction() {
                   @Override
                   public void update() {
                       isDone = true;
                       c.upgrade();
                       CardModifierManager.addModifier(c, new RetainMod());
                       c.superFlash();
                   }
               });
           }
       }));
    }


    public void upp() {
        selfRetain = true;
    }
}