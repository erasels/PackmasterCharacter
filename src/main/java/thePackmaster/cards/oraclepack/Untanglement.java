package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.oraclepack.PredictAction;
import thePackmaster.cardmodifiers.oraclepack.BlockPredictMod;
import thePackmaster.cardmodifiers.oraclepack.DrawPredictMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Untanglement extends AbstractOracleCard {

    public final static String ID = makeID("Untanglement");
    // intellij stuff power, self, rare, , , , , 3, 1

    public Untanglement() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new PredictAction(new DrawPredictMod(magicNumber)));
    }

    public void upp() {
        upgradeDamage(5);
    }
}
