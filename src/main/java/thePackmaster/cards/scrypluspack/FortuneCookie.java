package thePackmaster.cards.scrypluspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FortuneCookie extends AbstractScryPlusCard{

    public final static String ID = makeID(FortuneCookie.class.getSimpleName());
    public FortuneCookie() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryCallbackAction(magicNumber, list ->
        {
            if (!list.isEmpty()) {
                addToBot(new DrawCardAction(1));
            }
        }));
    }
}
