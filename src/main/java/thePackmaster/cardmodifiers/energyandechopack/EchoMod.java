package thePackmaster.cardmodifiers.energyandechopack;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static thePackmaster.util.Wiz.atb;

public class EchoMod extends AbstractCardModifier {
    public static String ID = "anniv5:EchoMod";

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractCard c = card.makeCopy();
        if (card.upgraded)
            c.upgrade();

        CardModifierManager.addModifier(c, new ExhaustMod());
        CardModifierManager.addModifier(c, new EchoedEtherealMod());
        CardModifierManager.addModifier(c, new GlowEchoMod());

        atb(new MakeTempCardInHandAction(c));
    }

    public AbstractCardModifier makeCopy() {
        return new EchoMod();
    }
}
