package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.odditiespack.AutoBattlerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class SeeingTheThreads extends AbstractOracleCard {

    public final static String ID = makeID("SeeingTheThreads");
    // intellij stuff power, self, rare, , , , , 3, 1

    public SeeingTheThreads() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 11;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ScryAction(magicNumber));
        addToBot(new DrawCardAction(1));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}
