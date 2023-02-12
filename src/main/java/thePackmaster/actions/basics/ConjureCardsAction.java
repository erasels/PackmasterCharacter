package thePackmaster.actions.basics;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cardmodifiers.basicspack.DuplicateModifier;

public class ConjureCardsAction extends AbstractGameAction {

    private final boolean freeToPlayOnce;

    private final AbstractPlayer p;

    private final int energyOnUse;

    private final AbstractCard card;

    public ConjureCardsAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse, AbstractCard card) {
        this.card = card;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if(effect>0) {
            CardModifierManager.addModifier(card, new DuplicateModifier(effect));
        }
        addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
        if (!this.freeToPlayOnce)
            this.p.energy.use(EnergyPanel.totalCount);
        this.isDone = true;
    }
}
