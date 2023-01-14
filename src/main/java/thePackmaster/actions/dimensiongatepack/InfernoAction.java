package thePackmaster.actions.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import thePackmaster.cards.dimensiongatepack.Inferno;
import thePackmaster.util.Wiz;
import thePackmaster.util.cardvars.HoardField;

public class InfernoAction extends AbstractGameAction {

    Inferno card = null;
    public InfernoAction(Inferno IN_ferno) {
        card = IN_ferno;
    }

    @Override
    public void update() {
        this.isDone = true;

        if (card.energyOnUse < EnergyPanel.totalCount) {
            card.energyOnUse = EnergyPanel.totalCount;
        }
        if (Wiz.p().hasRelic(ChemicalX.ID)) {
            card.energyOnUse = card.energyOnUse + 2;
        }
        if (HoardField.hoard.get(card) > 0) {
            HoardField.decrement(card, card.energyOnUse);
            if (HoardField.hoard.get(card) <= 0) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(Wiz.p(), new ScreenOnFireEffect(), 1.0F));
                Wiz.doAllDmg(card, AttackEffect.FIRE, false);
                HoardField.resetValueToBase(card);
            }
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(Wiz.p(), new ScreenOnFireEffect(), 1.0F));
            Wiz.doAllDmg(card, AttackEffect.FIRE, false);
            HoardField.resetValueToBase(card);
        }
        if (!card.freeToPlayOnce) {
            AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
        }
    }
}
