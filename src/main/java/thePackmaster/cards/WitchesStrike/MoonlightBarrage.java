package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.MoonlightBarrageAction;
import thePackmaster.actions.witchesstrikepack.MysticFlourishAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightBarrage extends AbstractPackmasterCard {
    public final static String ID = makeID("MoonlightBarrage");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MoonlightBarrage() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MoonlightBarrageAction(new DamageInfo(p,baseDamage)));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
