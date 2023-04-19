package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.lockonpack.LightsOutAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LightsOut extends AbstractLockonCard {

    public final static String ID = makeID(LightsOut.class.getSimpleName());

    public LightsOut() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LightsOutAction(upgraded));
    }
}
