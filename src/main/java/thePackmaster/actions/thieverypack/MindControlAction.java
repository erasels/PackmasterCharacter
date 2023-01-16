package thePackmaster.actions.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import thePackmaster.powers.thieverypack.MindControlledPower;

public class MindControlAction extends AbstractGameAction {
	public float t;
	private final boolean freeToPlayOnce;
	private final int magic;
	private final AbstractPlayer p;
	private final AbstractMonster targetMonster;
	private final int energyOnUse;
	private final String speech;

	public MindControlAction(AbstractPlayer p, AbstractMonster m, int magic, boolean freeToPlayOnce, int energyOnUse, String speech) {
		this.p = p;
		this.targetMonster = m;
		this.magic = magic;
		this.freeToPlayOnce = freeToPlayOnce;
		this.actionType = ActionType.SPECIAL;
		this.energyOnUse = energyOnUse;
		this.speech = speech;
	}

	@Override
	public void update() {
		isDone = true;

		int effect = EnergyPanel.totalCount;
		if (energyOnUse != -1) {
			effect = energyOnUse;
		}

		AbstractRelic r = p.getRelic(ChemicalX.ID);
		if (r != null) {
			effect += 2;
			r.flash();
		}
		if (targetMonster.currentHealth > magic * effect) {
			AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, speech, true));
			return;
		}

		if (!freeToPlayOnce) {
			p.energy.use(EnergyPanel.totalCount);
		}

		addToTop(new ApplyPowerAction(targetMonster, p, new MindControlledPower(targetMonster)));
	}
}
