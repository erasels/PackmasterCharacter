package thePackmaster.cards.conjurerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.conjurerpack.PlayRandomCardAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Enervate extends ConjurerCard
{
    public final static String ID = makeID("DustTornado");
    private static final int MAGIC = 2;


    public Enervate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
        {
            int decAmount = magicNumber;
            AbstractPower strPower = m.getPower(StrengthPower.POWER_ID);
            if (strPower != null)
            {
                decAmount += strPower.amount;
            }
            this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -decAmount), -decAmount));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
