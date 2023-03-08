package thePackmaster.cardmodifiers.oraclepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.oraclepack.Prophecy;
import thePackmaster.util.Wiz;

public class BlockPredictMod extends AbstractPredictMod {

    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("BlockPredictMod"));

    @Override
    public String identifier(AbstractCard card) {
        return SpireAnniversary5Mod.makeID("BlockPredictMod");
    }

    public BlockPredictMod(int amount) {
        this.amount = amount;
        priority = -10; //try to make block happen first
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.baseBlock = amount;
        ((Prophecy)card).addTarget(AbstractCard.CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BlockPredictMod(amount);
    }

    @Override
    public void updateAmount(AbstractCard card, int newAmount) {
        card.baseBlock = newAmount;
        amount = newAmount;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return strings.TEXT[0] + rawDescription;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new GainBlockAction(AbstractDungeon.player, card.block));
    }
}
