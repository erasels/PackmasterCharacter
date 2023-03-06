package thePackmaster.cards.spherespack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.spherespack.HopePower;

public class Hope extends AbstractSpheresCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Hope");
    private static final int COST = 1;
    private static final int AMOUNT = 1;

    public Hope() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
    }

    @Override
    public void upp() {
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HopePower(p, this.magicNumber)));
        this.addToBot(new IncreaseMaxOrbAction(1));
        this.addToBot(new ChannelAction(new thePackmaster.orbs.spherespack.Hope()));
    }
}
