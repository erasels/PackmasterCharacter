package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.oraclepack.PredictAction;
import thePackmaster.cardmodifiers.oraclepack.DamagePredictMod;
import thePackmaster.cardmodifiers.oraclepack.StrengthPredictMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BadOmen extends AbstractOracleCard {

    public final static String ID = makeID("BadOmen");
    // intellij stuff power, self, rare, , , , , 3, 1

    public BadOmen() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        addToBot(new PredictAction(new StrengthPredictMod(magicNumber)));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
