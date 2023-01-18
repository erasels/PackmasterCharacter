package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.LuciferiumAddictionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Luciferium extends AbstractRimCard {
    public final static String ID = makeID(Luciferium.class.getSimpleName());

    private static int STRENGTH = 5;

    public Luciferium() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
        cardsToPreview = new Despair();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(2));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH), STRENGTH));
        addToBot(new ApplyPowerAction(p, p, new LuciferiumAddictionPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}