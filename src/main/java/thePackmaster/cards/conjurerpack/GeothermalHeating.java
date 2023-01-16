package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.intothebreachpack.AcidPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class GeothermalHeating extends ConjurerCard
{
    public final static String ID = makeID(GeothermalHeating.class);
    private static final int BLOCK = 5;
    private static final int MAGIC = 3;


    public GeothermalHeating() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (m != null)
        {
            int count = 0;
            for (AbstractPower power : m.powers)
                if (power.type == AbstractPower.PowerType.DEBUFF)
                    count++;
            for (int i = 0; i < count; i++)
                Wiz.doBlk(magicNumber);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
