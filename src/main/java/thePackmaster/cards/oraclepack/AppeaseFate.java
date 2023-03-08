package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.oraclepack.PredictAction;
import thePackmaster.cardmodifiers.oraclepack.BlockPredictMod;
import thePackmaster.cardmodifiers.oraclepack.DamagePredictMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AppeaseFate extends AbstractOracleCard {

    public final static String ID = makeID("AppeaseFate");
    // intellij stuff power, self, rare, , , , , 3, 1

    public AppeaseFate() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new PredictAction(new BlockPredictMod(magicNumber)));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}
