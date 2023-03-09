package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.oraclepack.PredictAction;
import thePackmaster.cardmodifiers.oraclepack.AoePredictMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Doomsayer extends AbstractOracleCard {

    public final static String ID = makeID("Doomsayer");
    // intellij stuff power, self, rare, , , , , 3, 1

    public Doomsayer() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 40;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PredictAction(new AoePredictMod(magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(10);
    }
}
