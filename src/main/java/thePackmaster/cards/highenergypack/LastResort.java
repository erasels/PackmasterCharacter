package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.actions.EasyXCostAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class LastResort extends AbstractHighEnergyCard {
    public final static String ID = makeID("LastResort");
    // intellij stuff skill, self, uncommon, , , 6, 2, , 

    public LastResort() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (effect == 0) {
                applyToSelfTop(new EnergizedBluePower(p, 1));
            } else {
                for (int i = 0; i < effect; i++) {
                    att(new GainBlockAction(p, block));
                }
            }
            return true;
        }));
    }

    public void upp() {
        upgradeBlock(2);
    }
}