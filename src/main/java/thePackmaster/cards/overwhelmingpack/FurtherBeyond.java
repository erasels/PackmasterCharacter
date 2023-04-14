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
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);

        this.damage = this.baseDamage = 30;

        this.isMultiDamage = true;
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
        allDmg(AbstractGameAction.AttackEffect.SLASH_HEAVY);
        //dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    public void upp() {
        upgradeDamage(10);
    }
}