package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.MoodPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Catharsis extends AbstractPackmasterCard {
    public final static String ID = makeID(Catharsis.class.getSimpleName());

    public Catharsis() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        MoodPower.BreaksAndInspirations.mentalBreak(p);
        addToBot(new WaitAction(.25f));
        for (int i = 0; i < magicNumber; i++)
        {
            MoodPower.BreaksAndInspirations.inspiration(p);
            addToBot(new WaitAction(.25f));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}