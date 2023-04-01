package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.lockonpack.BruteforceAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bruteforce extends AbstractLockonCard {

    public final static String ID = makeID(Bruteforce.class.getSimpleName());

    public Bruteforce() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
    }

    @Override
    public void upp()
    {
        exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new BruteforceAction(magicNumber));
    }
}
