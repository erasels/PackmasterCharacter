package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.intriguepack.SpotlightAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Spotlight extends AbstractIntrigueCard {
    public final static String ID = makeID("Spotlight");

    public Spotlight() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            Wiz.atb(new SpotlightAction(1,upgraded));
    }

    public void upp() {
    }
}
