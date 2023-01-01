package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Connection extends AbstractPackmasterCard {
    public final static String ID = makeID(Connection.class.getSimpleName());

    public Connection() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new BetterDrawPileToHandAction(this.magicNumber));
        atb(new ApplyPowerAction(p, p, new EquilibriumPower(p, 1), 1));
    }

    public void upp() {
        isInnate = true;
    }
}