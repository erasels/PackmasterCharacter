package thePackmaster.cards.rimworldpack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.MoodPower;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Overwork extends AbstractPackmasterCard {
    public final static String ID = makeID(Overwork.class.getSimpleName());

    public Overwork() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new GainEnergyAction(upgraded?2:1));
        addToBot(new ApplyPowerAction(p, p, new MoodPower(p, -magicNumber), -magicNumber));
    }

    public void upp() {
    }
}