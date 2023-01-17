package thePackmaster.cards.warriorpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.applyToSelf;

public class WarSong extends AbstractPackmasterCard {

    public final static String ID = makeID(WarSong.class.getSimpleName());

    private static final int COST = 1;

    public WarSong(){
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 2;
        secondMagic = baseSecondMagic = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.secondMagic), this.secondMagic));
    }

    @Override
    public void upp() {
        upMagic(1);
        upSecondMagic(1);
    }
}
