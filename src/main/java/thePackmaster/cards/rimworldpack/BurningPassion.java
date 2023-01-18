package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.BurningPassionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BurningPassion extends AbstractRimworldCard {
    public final static String ID = makeID(BurningPassion.class.getSimpleName());

    public BurningPassion() {
        super(ID, 4, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BurningPassionPower(p, 2), 1));
    }

    public void upp() {
        upgradeBaseCost(3);
    }
}