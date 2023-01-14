package thePackmaster.actions.thieverypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SmokescreenAction extends AbstractGameAction {
	boolean isHalf;

	public SmokescreenAction(boolean isHalf) {
		this.isHalf = isHalf;
	}

	@Override
	public void update() {
		isDone = true;

		for (int i = AbstractDungeon.getMonsters().monsters.size() - 1; i >= 0; i--) {
			AbstractMonster m = AbstractDungeon.getMonsters().monsters.get(i);
			if (!m.isDeadOrEscaped()) {
				int hpLoss = m.currentBlock / (isHalf ? 2 : 1);
				addToTop(new LoseHPAction(m, m, hpLoss, AttackEffect.POISON));
			}
		}
	}
}
