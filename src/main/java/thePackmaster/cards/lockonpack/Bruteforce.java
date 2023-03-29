package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.actions.lockonpack.BruteforceAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bruteforce extends AbstractLockonCard {

    public final static String ID = makeID(Bruteforce.class.getSimpleName());

    public Bruteforce() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
    }

    @Override
    public void upp()
    {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new BruteforceAction(magicNumber));
    }
}
