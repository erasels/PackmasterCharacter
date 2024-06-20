package thePackmaster.cards.pinnaclepack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Rea__dyCommonSkill extends AbstractPinnacleCard {

    public final static String ID = makeID("Rea__dyCommonSkill");

    private static final int MAGIC = 3;
    private static final int MAGIC2 = 2;

    public Rea__dyCommonSkill() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
        ExhaustiveVariable.setBaseValue(this, 2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, secondMagic), secondMagic));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, secondMagic), secondMagic));
        if (this.upgraded) {
            addToBot(new DrawCardAction(1));
        }
    }

    @Override
    public void upp() {}
}
