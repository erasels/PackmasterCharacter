package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.conjurerpack.PlayRandomCardAction;
import thePackmaster.powers.bellordpack.CuckooPower;
import thePackmaster.powers.conjurerpack.GeomorphologyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Geomorphology extends ConjurerCard
{
    public final static String ID = makeID(Geomorphology.class);
    private static final int MAGIC = 1;


    public Geomorphology() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GeomorphologyPower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
