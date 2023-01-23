package thePackmaster.cardmodifiers.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.Wiz;

public class TempDexterityGemMod extends AbstractMadScienceModifier {

    public TempDexterityGemMod() {
        super();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[5];
    }
    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(Wiz.p(), Wiz.p(), new DexterityPower(Wiz.p(), 2), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(Wiz.p(), Wiz.p(), new LoseDexterityPower(Wiz.p(), 2), 2));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new TempDexterityGemMod();
    }
}