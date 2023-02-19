package thePackmaster.cards.marisapack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.dimensiongate3pack.TempHPRegenPower;
import thePackmaster.util.Wiz;

import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Inertia extends AbstractMarisaCard implements AmplifyCard{
    public final static String ID = makeID(Inertia.class.getSimpleName());
    private static final int MAGIC = 4, UPG_MAGIC = 1, ADD = 2;
    private static TooltipInfo ablation;

    public Inertia() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = ADD;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new TempHPRegenPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeSecondMagic(UPG_MAGIC);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return true;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new TempHPRegenPower(p, magicNumber + secondMagic));
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if(ablation == null) {
            AbstractPower p = new TempHPRegenPower(null, 999);
            ablation = new TooltipInfo(p.name, p.description.replace("999", "X"));
        }
        return Arrays.asList(ablation);
    }
}
