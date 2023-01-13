package thePackmaster.cardmodifiers.energyandechopack;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static thePackmaster.util.Wiz.atb;

public class GlowEchoMod extends AbstractCardModifier {
    public static String ID = "anniv5:GlowEchoMod";
    public AbstractCardModifier makeCopy() {
        return new GlowEchoMod();
    }
    public String identifier(AbstractCard card) {
        return ID;
    }
}
