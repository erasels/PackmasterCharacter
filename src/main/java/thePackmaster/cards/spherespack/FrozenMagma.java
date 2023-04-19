package thePackmaster.cards.spherespack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.spherespack.Blaze;
import thePackmaster.orbs.spherespack.Polar;

public class FrozenMagma extends AbstractSpheresCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FrozenMagma");
    private static final int COST = 2;

    public FrozenMagma() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 3;
    }

    @Override
    public void upp() {
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChannelAction(new Polar()));
        this.addToBot(new ChannelAction(new Frost()));
        this.addToBot(new ChannelAction(new Blaze()));
    }
}
