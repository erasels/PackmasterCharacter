package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pixiepack.FastHandsPower;
import thePackmaster.powers.pixiepack.UpgradedFastHandsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FastHands extends AbstractPixieCard {
    public final static String ID = makeID("FastHands");

    public FastHands() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upp(){}

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!upgraded) addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new FastHandsPower(abstractPlayer, 1)));
        else addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new UpgradedFastHandsPower(abstractPlayer, 1)));
    }
}
