package thePackmaster.actions.cosmoscommandpack;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.cardmodifiers.cosmoscommand.PurgeModifier;

import static thePackmaster.util.Wiz.adp;

public class ExhumeAndAddPurgeAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhumeAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public ExhumeAndAddPurgeAction() {
        this.p = adp();
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                p.createHandIsFullDialog();
                this.isDone = true;
                return;
            }

            if (p.exhaustPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (p.exhaustPile.size() == 1) {
                AbstractCard onlyCard = p.exhaustPile.getTopCard();
                exhumeCardAndAddPurge(onlyCard);
                this.isDone = true;
                return;
            }

            for (AbstractCard abstractCard : this.p.exhaustPile.group) {
                abstractCard.stopGlowing();
                abstractCard.unhover();
                abstractCard.unfadeOut();
            }

            AbstractDungeon.gridSelectScreen.open(p.exhaustPile, 1, TEXT[0], false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                exhumeCardAndAddPurge(c);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            for (AbstractCard c : p.exhaustPile.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
        }
        tickDuration();
    }

    public void exhumeCardAndAddPurge(AbstractCard c) {
        c.initializeDescription();
        c.current_x = CardGroup.DISCARD_PILE_X;
        c.current_y = 0.0F;
        p.hand.addToHand(c);
        if (p.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL)
            c.setCostForTurn(-9);
        p.exhaustPile.removeCard(c);
        CardModifierManager.addModifier(c, new PurgeModifier());
        c.applyPowers();
        c.unhover();
        c.unfadeOut();
        p.hand.refreshHandLayout();
    }
}