package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class ManifestMeal extends AbstractPackmasterCard {
    public final static String ID = makeID("ManifestMeal");
    // intellij stuff attack, enemy, uncommon, 5, 2, , , , 

    public ManifestMeal() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                dmgTop(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            }
            Food delociousFoob = new Food();
            delociousFoob.setX(effect);
            att(new MakeTempCardInDrawPileAction(delociousFoob, 1, true, true));
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(2);
    }
}