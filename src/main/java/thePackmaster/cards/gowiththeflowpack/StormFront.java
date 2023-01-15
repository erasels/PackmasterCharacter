package thePackmaster.cards.gowiththeflowpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.p;

public class StormFront extends AbstractHydrologistCard {
    public final static String ID = makeID("StormFront");

    public StormFront() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, Subtype.STEAM);
        damage = baseDamage = 30;
        magicNumber = baseMagicNumber = 10;
        retain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        hydrologistDamage(p, m, 30);
    }

    @Override
    public void triggerOnManualDiscard() {
        baseDamage += magicNumber;
        addToBot(new MoveCardsAction(p().hand, p().discardPile, c -> c == this));
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}