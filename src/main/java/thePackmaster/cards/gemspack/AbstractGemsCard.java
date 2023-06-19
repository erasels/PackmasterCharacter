package thePackmaster.cards.gemspack;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGemsCard extends AbstractPackmasterCard {

    public AbstractGemsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "gems");

    }

    public AbstractGemsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    public abstract AbstractCardModifier myMod();


    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tooltips = new ArrayList<>();
        tooltips.add(new TooltipInfo(BaseMod.getKeywordProper("anniv5:modify"), BaseMod.getKeywordDescription("anniv5:modify")));
        return tooltips;
    }

    @Override
    public void upp() {
    }
}
