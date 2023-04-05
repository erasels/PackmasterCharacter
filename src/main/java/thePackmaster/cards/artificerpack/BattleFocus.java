package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.artificerpack.BattleFocusPower;

public class BattleFocus extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("BattleFocus");

    public BattleFocus(){
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p,p,new BattleFocusPower(magicNumber)));
    }
}
