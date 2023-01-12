package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.actions.unique.ExhaustAllNonAttackAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.rippack.ExhaustAllArtCardsAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class LeftBrain extends AbstractRipCard {
    public final static String ID = makeID("LeftBrain");


    public LeftBrain() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    public void upp() {
        isUnnate = true;
        uDesc();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAllArtCardsAction());
    }
}
