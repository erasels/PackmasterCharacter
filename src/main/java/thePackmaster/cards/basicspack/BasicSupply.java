package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Defend;
import thePackmaster.cards.Strike;
import thePackmaster.powers.basicspack.BasicSupplyPlusPower;
import thePackmaster.powers.basicspack.BasicSupplyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicSupply extends AbstractBasicsCard {
    public final static String ID = makeID("BasicSupply");

    public BasicSupply() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        AbstractCard strike = new Strike();
        strike.upgrade();
        this.cardToPreview.add(strike);
        AbstractCard defend = new Defend();
        defend.upgrade();
        this.cardToPreview.add(defend);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded) {
            addToBot(new ApplyPowerAction(p, p, new BasicSupplyPlusPower(1), 1));
        } else addToBot(new ApplyPowerAction(p, p, new BasicSupplyPower(1), 1));
    }

    @Override
    public void upp() {
    }
}
