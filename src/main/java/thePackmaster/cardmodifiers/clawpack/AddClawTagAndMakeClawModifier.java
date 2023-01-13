package thePackmaster.cardmodifiers.clawpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sun.util.resources.cldr.ti.CalendarData_ti_ER;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.cards.clawpack.GhostClaw;
import thePackmaster.util.Wiz;

public class AddClawTagAndMakeClawModifier extends AbstractMadScienceModifier {

    public AddClawTagAndMakeClawModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (!card.hasTag(SpireAnniversary5Mod.CLAW)){
            card.tags.add(SpireAnniversary5Mod.CLAW);
            card.applyPowers();
        }
        if (card.cardsToPreview == null){
            card.cardsToPreview = new GhostClaw();
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("AddClawTagAndMakeClawModifier")).TEXT[0];
    }

    @Override
    public void alterName(AbstractCard card) {
        modifyName(card.name + " Claw", card);
        super.alterName(card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        AbstractCard c = new GhostClaw();
        Wiz.atb(new MakeTempCardInHandAction(c));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddClawTagAndMakeClawModifier(value);
    }
}