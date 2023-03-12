package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.oraclepack.PredictAction;
import thePackmaster.cardmodifiers.oraclepack.DamagePredictMod;
import thePackmaster.cardmodifiers.oraclepack.StrengthPredictMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReadTheSigns extends AbstractOracleCard {

    public final static String ID = makeID("ReadTheSigns");
    // intellij stuff power, self, rare, , , , , 3, 1

    public ReadTheSigns() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
        baseMagicNumber = magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new PredictAction(new DamagePredictMod(magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}
