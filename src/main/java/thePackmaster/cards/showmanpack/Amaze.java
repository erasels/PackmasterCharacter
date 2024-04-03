package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Amaze extends AbstractPackmasterCard {
    public final static String ID = makeID("Amaze");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Amaze() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : Wiz.getEnemies()){
            if (mo.hasPower(WeakPower.POWER_ID)){
                addToBot(new LoseHPAction(mo, p, magicNumber * mo.getPower(WeakPower.POWER_ID).amount));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}