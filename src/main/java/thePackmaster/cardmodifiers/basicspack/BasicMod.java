//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package thePackmaster.cardmodifiers.basicspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import org.apache.commons.lang3.StringUtils;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicMod extends AbstractCardModifier {
    public static String ID = makeID("BasicCardModifier");

    public BasicMod() {
    }

    public boolean shouldApply(AbstractCard card) {
        return card.rarity != AbstractCard.CardRarity.BASIC;
    }

    public void onInitialApplication(AbstractCard card) {
        card.rarity = AbstractCard.CardRarity.BASIC;
    }

    public AbstractCardModifier makeCopy() {
        return new BasicMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
