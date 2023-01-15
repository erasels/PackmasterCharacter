package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class Crystallize extends AbstractPackmasterCard {

    public final static String ID = makeID(Crystallize.class.getSimpleName());

    private static final int COST = 1;

    public Crystallize(){
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyPowers();
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = EnergyPanel.totalCount * this.magicNumber;
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upMagic(1);
    }
}
