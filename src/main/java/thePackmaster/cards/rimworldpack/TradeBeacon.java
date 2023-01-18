package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.SpikeTrapPower;
import thePackmaster.powers.rimworldpack.TradeBeaconPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TradeBeacon extends AbstractRimCard {
    public final static String ID = makeID(TradeBeacon.class.getSimpleName());

    public TradeBeacon() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new TradeBeaconPower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}