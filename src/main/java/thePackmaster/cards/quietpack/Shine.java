package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.quietpack.ShineAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Shine extends AbstractQuietCard {
    public final static String ID = makeID("Shine");

    public Shine() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        selfRetain = true;
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new ShineAction(this, this.upgraded));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void upp() {
    }

}


