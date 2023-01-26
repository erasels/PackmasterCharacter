package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.gemspack.ReduceDebuffsAction;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;

public class CleanseGemMod extends AbstractMadScienceModifier {

    public CleanseGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[0];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new ReduceDebuffsAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CleanseGemMod();
    }
}