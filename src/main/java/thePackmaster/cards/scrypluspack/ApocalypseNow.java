package thePackmaster.cards.scrypluspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ApocalypseNow extends AbstractScryPlusCard{

    public final static String ID = makeID(ApocalypseNow.class.getSimpleName());
    public ApocalypseNow() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        setDmg(25);
        setMN(12);
    }

    @Override
    public void upp() {
        upgradeDamage(7);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryCallbackAction(magicNumber, list ->
        {
            if (list.size() > 7) {
                addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
            }
        }));
    }
}
