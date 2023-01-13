package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.EnergyAndEchoPack;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class MagnetRise extends AbstractPackmasterCard {

    public final static String ID = makeID(MagnetRise.class.getSimpleName());

    private static final int COST = 1;
    public MagnetRise(){
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void applyPowers() {
        if (EnergyAndEchoPack.generatedEnergy > 1)
            this.setCostForTurn(0);
        super.applyPowers();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}
