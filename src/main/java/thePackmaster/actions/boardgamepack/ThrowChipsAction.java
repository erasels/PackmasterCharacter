package thePackmaster.actions.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class ThrowChipsAction extends AbstractGameAction {
    private final boolean free;
    private final int energyOnUse;

    private int damage;
    private AbstractCard card;

    public ThrowChipsAction(AbstractCard card, boolean freeToPlayOnce, int energyOnUse, int damage) {
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DAMAGE;
        free = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        this.damage = damage;
        this.card = card;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic(ChemicalX.ID)) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for (int i = 0; i < effect; i++)
                att(new AttackDamageRandomEnemyAction(card, AttackEffect.SLASH_VERTICAL));

            if (!free)
                adp().energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
