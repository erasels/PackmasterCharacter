package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.rippack.LeftBrainAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class LeftBrain extends AbstractRipCard {
    public final static String ID = makeID("LeftBrain");


    public LeftBrain() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        isUnnate = true;
        uDesc();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new LeftBrainAction());
    }
}
