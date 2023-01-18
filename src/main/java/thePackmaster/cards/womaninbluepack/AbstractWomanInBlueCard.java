package thePackmaster.cards.womaninbluepack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWomanInBlueCard extends AbstractPackmasterCard {
    public AbstractWomanInBlueCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "womaninblue");

    }

    public String getPotionResourcePath(String imgPath) {
        return SpireAnniversary5Mod.makeImagePath("vfx/womaninbluepack/" + imgPath);
    }

    public void setExhaustive2() {
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
        exhaust = false;
    }


}
