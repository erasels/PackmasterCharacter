package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.CutThroughFate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.MysticFlourishAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MysticFlourish extends AbstractWitchStrikeCard {
    public final static String ID = makeID("MysticFlourish");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MysticFlourish() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        addToBot(new MysticFlourishAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return CutThroughFate.ID;
    }
}
