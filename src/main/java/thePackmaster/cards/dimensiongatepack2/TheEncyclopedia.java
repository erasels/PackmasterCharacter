package thePackmaster.cards.dimensiongatepack2;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongate2pack.EncyclopediaAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardObelisk;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TheEncyclopedia extends AbstractDimensionalCardObelisk {
    public final static String ID = makeID("TheEncyclopedia");

    public TheEncyclopedia() {
        super(ID, 1, CardRarity.RARE, CardType.SKILL, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 6;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new EncyclopediaAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0]));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }

}