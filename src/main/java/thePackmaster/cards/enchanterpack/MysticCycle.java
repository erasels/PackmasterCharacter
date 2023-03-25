package thePackmaster.cards.enchanterpack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.enchanterpack.LimitedGambleAction;

public class MysticCycle extends AbstractEnchanterCard {

    public static final String ID = SpireAnniversary5Mod.makeID("MysticCycle");

    public MysticCycle(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new LimitedGambleAction(2));
        addToBot(new GainEnergyAction(magicNumber));
    }

}
