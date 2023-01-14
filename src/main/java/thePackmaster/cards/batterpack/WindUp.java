package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WindUp extends AbstractPackmasterCard {
    public final static String ID = makeID("WindUp");

    public WindUp() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = 1;
        magicNumber = this.baseMagicNumber;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new VulnerablePower(m,this.magicNumber,false));
        this.addToBot(new GainEnergyAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
