package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.creativitypack.FirewallPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Firewall extends AbstractCreativityCard {

    public final static String ID = makeID(Firewall.class.getSimpleName());

    public Firewall() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upp() {
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p, p, new FirewallPower(p)));
    }
}
