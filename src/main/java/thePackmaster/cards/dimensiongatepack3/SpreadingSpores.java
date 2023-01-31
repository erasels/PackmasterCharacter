package thePackmaster.cards.dimensiongatepack3;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.powers.dimensiongate3pack.TempHPRegenPower;
import thePackmaster.util.Wiz;

import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SpreadingSpores extends AbstractDimensionalCardTrain {
    private static TooltipInfo ablation;

    public final static String ID = makeID("SpreadingSpores");

    public SpreadingSpores() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 2;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if(ablation == null) {
            AbstractPower p = new TempHPRegenPower(null, 999);
            ablation = new TooltipInfo(p.name, p.description.replace("999", "X"));
        }
        return Arrays.asList(ablation);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ThornsPower(p, secondMagic));
        Wiz.applyToSelf(new TempHPRegenPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}