package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.basicspack.FetchBasicCardsAction;
import thePackmaster.powers.basicspack.BasicPerfectionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Simplicity extends AbstractBasicsCard{
    public final static String ID = makeID("Simplicity");

    public Simplicity() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FetchBasicCardsAction(this.magicNumber));
    }

    public void upp(){
        upgradeMagicNumber(1);
    }
}
