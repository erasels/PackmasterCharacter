package thePackmaster.cardmodifiers.rippack;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TextCardModifier extends AbstractCardModifier {

    public static String ID = makeID("TextCardModifier");

    public TextCardModifier() { }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, TextCardModifier.ID);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        ArrayList<TooltipInfo> tooltips = new ArrayList<>();
        tooltips.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("text card")), BaseMod.getKeywordDescription(makeID("text card"))));
        return tooltips;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new TextCardModifier();
    }

}
