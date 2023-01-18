package thePackmaster.cards.warriorpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Bolas extends AbstractPackmasterCard {

    public final static String ID = makeID(Bolas.class.getSimpleName());

    private static final int COST = 0;

    public Bolas(){
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 3;
        secondMagic = baseSecondMagic = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new SlowPower(m, 0));
        atb(new GainBlockAction(m, p, magicNumber));
        atb(new DrawCardAction(secondMagic));
    }

    @Override
    public void upp() {
        upMagic(-2);
        upSecondMagic(1);
    }
}
