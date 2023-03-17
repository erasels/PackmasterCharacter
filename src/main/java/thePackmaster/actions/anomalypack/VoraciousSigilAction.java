package thePackmaster.actions.anomalypack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.RepeatCardAction;
import thePackmaster.cardmodifiers.anomalypack.SigilModifier;
import thePackmaster.util.Wiz;

public class VoraciousSigilAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard sigil;

    public VoraciousSigilAction(AbstractCard sigil) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.sigil = sigil;
    }

    public void update() {
        if (this.p.hand.group.stream().anyMatch(c -> c.type == AbstractCard.CardType.SKILL)) {
            AbstractCard card = this.p.hand.getRandomCard(AbstractCard.CardType.SKILL, true);
            Wiz.atb(new ExhaustSpecificCardAction(card, p.hand));
            CardModifierManager.addModifier(sigil, new SigilModifier(card));
            addToBot(new NewQueueCardAction(card.makeSameInstanceOf(), true, true, true));
        }
        this.isDone = true;
    }


}
