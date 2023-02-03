package thePackmaster.cardmodifiers.rippack;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RippableModifier extends AbstractCardModifier {

    public static String ID = makeID("RippableModifier");

    public static String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    boolean isInherent;

    public RippableModifier() {
        this(true);
    }

    public RippableModifier(boolean isInherent) {
        this.isInherent = isInherent;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return isInherent;
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return TEXT[0] + cardName;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        ArrayList<TooltipInfo> tooltips = new ArrayList<>();
        tooltips.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("rippable")), BaseMod.getKeywordDescription(makeID("rippable"))));
        return tooltips;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RippableModifier();
    }

    public static boolean isRippable(AbstractCard card) {
        return CardModifierManager.hasModifier(card, ID);
    }

}
