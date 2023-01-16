package thePackmaster.cards.rimworldpack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.MoodPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Relax extends AbstractPackmasterCard {
    public final static String ID = makeID(Relax.class.getSimpleName());

    public Relax() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        secondMagic = baseSecondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, secondMagic, false));
        addToBot(new ApplyPowerAction(p, p, new MoodPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}