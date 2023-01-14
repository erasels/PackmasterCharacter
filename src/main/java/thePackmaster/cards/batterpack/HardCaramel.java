package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.batterpack.HardCaramelPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HardCaramel extends AbstractPackmasterCard {
    public final static String ID = makeID("HardCaramel");

    public HardCaramel() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HardCaramelPower(p, 1), 1));
    }

    public void upp() {
        isInnate = true;
    }
}
