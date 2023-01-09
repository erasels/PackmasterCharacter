package thePackmaster.actions.entropypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.entropypack.RuinPower;

public class OblivionEvokeAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public OblivionEvokeAction(int amt) {
        this.p = AbstractDungeon.player;
        this.amount = amt;
    }

    @Override
    public void update() {
        this.isDone = true;

        AbstractMonster strongest = null;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped() && (strongest == null || (m.currentHealth > strongest.currentHealth))) {
                strongest = m;
            }
        }

        if (strongest != null) {
            addToTop(new ApplyPowerAction(strongest, p, new RuinPower(strongest, this.amount), this.amount));
        }
    }
}
