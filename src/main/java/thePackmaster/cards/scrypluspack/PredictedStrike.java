package thePackmaster.cards.scrypluspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PredictedStrike extends AbstractScryPlusCard{

    public final static String ID = makeID(PredictedStrike.class.getSimpleName());
    public PredictedStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        tags.add(CardTags.STRIKE);
        setDmg(7);
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, Wiz.getRandomSlash());
        addToBot(new ScryCallbackAction(magicNumber, list ->
            list.forEach(c -> Wiz.doDmg(m, damage, Wiz.getRandomSlash()))
        ));
    }
}
