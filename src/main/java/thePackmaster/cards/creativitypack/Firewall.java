package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.creativitypack.FirewallPower;
import thePackmaster.powers.creativitypack.MakeshiftSwordPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Firewall extends AbstractPackmasterCard {

    public final static String ID = makeID(Firewall.class.getSimpleName());

    public Firewall() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upp() {
        isInnate = true;
        uDesc();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p, p, new FirewallPower(p)));
    }
}
