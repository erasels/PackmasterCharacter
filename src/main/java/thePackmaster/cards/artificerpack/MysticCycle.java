package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.enchanterpack.LimitedGambleAction;

public class MysticCycle extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("MysticCycle");

    public MysticCycle(){
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new LimitedGambleAction(magicNumber));
    }

}
