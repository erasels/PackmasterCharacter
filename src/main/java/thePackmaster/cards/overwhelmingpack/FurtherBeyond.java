package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FurtherBeyond extends AbstractOverwhelmingCard {
    public final static String ID = makeID("FurtherBeyond");

    public FurtherBeyond() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);

        this.damage = this.baseDamage = 50;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m))
            return false;

        if (EnergyPanel.getCurrentEnergy() == 0) {
            return true;
        }
        else {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Add Vfx :)
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}