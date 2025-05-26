package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.rimworldpack.BurningPassionAction;
import thePackmaster.powers.rimworldpack.BurningPassionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BurningPassion extends AbstractRimCard {
    public final static String ID = makeID(BurningPassion.class.getSimpleName());

    public BurningPassion() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurningPassionAction(upgraded));
    }

    @Override
    public void upp() {

    }
}